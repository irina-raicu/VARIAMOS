package com.variamos.perspsupport.expressionsupport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstPairwiseRelation;
import com.variamos.perspsupport.opers.OpersAbstractElement;
import com.variamos.perspsupport.types.OperationSubActionExecType;
import com.variamos.perspsupport.types.OperationSubActionType;

/**
 * TODO A class to support sub actions to generalize the semantic
 * operationalization with GUI edition. Part of PhD work at University of Paris
 * 1
 * 
 * @author Juan C. Mu�oz Fern�ndez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-02-05
 */
public class OpersSubOperation extends OpersAbstractElement implements
		Comparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2965378108648056600L;
	private int position;
	private List<OpersSubOperationExpType> operationSubActionExpTypes;
	private Set<OpersIOAttribute> inAttributes;
	private Set<OpersIOAttribute> outAttributes;

	// private List<OperationLabeling> operationLabelings;
	private OperationSubActionType operationSubActionType;

	public OpersSubOperation(int position, String identifier, boolean iterable,
			OperationSubActionType operationSubActionType) {
		super(identifier);
		this.position = position;
		this.operationSubActionType = operationSubActionType;
		operationSubActionExpTypes = new ArrayList<OpersSubOperationExpType>();
		inAttributes = new HashSet<OpersIOAttribute>();
		outAttributes = new HashSet<OpersIOAttribute>();
	}

	public OpersSubOperation() {
		super(null);
		// TODO Auto-generated constructor stub
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public List<OpersSubOperationExpType> getOperationSubActionExpTypes() {
		return operationSubActionExpTypes;
	}

	public void setOperationSubActionExpTypes(
			List<OpersSubOperationExpType> operationSubActionExpType) {
		this.operationSubActionExpTypes = operationSubActionExpType;
	}

	public OperationSubActionType getOperationSubActionType() {
		return operationSubActionType;
	}

	public void setOperationSubActionType(
			OperationSubActionType operationSubActionType) {
		this.operationSubActionType = operationSubActionType;
	}

	// public void addOperationLabeling(OperationLabeling operationLabeling) {
	// operationLabelings.add(operationLabeling);
	// }

	public void addOperationSubActionExpType(
			OpersSubOperationExpType operationSubActionExpType) {
		operationSubActionExpTypes.add(operationSubActionExpType);
	}

	public List<String> getOperationSubActionExpTypesNames() {
		List<String> out = new ArrayList<String>();
		for (OpersSubOperationExpType oper : operationSubActionExpTypes) {
			out.add(this.getIdentifier() + "-"
					+ oper.getExpressionType().toString());
		}
		return out;

	}

	public OpersSubOperationExpType getOperationSubActionExpType(
			OperationSubActionExecType expressionType) {
		for (OpersSubOperationExpType oper : operationSubActionExpTypes) {
			if (oper.getExpressionType().equals(expressionType))
				return oper;
		}
		return null;
	}

	public boolean hasInVariable(String concept, String attribute) {
		for (OpersIOAttribute var : inAttributes)
			if (var.getConceptId().equals(concept)
					&& var.getAttributeId().equals(attribute))
				return true;
		return false;
	}

	public Set<OpersIOAttribute> getInAttributes() {
		return inAttributes;
	}

	public void setInAttributes(Set<OpersIOAttribute> inVariables) {
		this.inAttributes = inVariables;
	}

	public Set<OpersIOAttribute> getOutAttributes() {
		return outAttributes;
	}

	public void setOutAttribute(Set<OpersIOAttribute> outVariables) {
		this.outAttributes = outVariables;
	}

	public boolean hasOutVariable(String concept, String attribute) {
		for (OpersIOAttribute var : outAttributes)
			if (var.getConceptId().equals(concept)
					&& var.getAttributeId().equals(attribute))
				return true;
		return false;
	}

	public void addInAttribute(OpersIOAttribute attribute) {
		inAttributes.add(attribute);
	}

	public void addOutAttribute(OpersIOAttribute attribute) {
		outAttributes.add(attribute);
	}

	// 1 include, -1 exclude, 0 unknown
	public int validateAttribute(InstElement instElement, String attribute,
			boolean in) {
		int include = 99999;
		int exclude = 99999;
		List<String> parents = new ArrayList<String>();
		InstElement element = instElement;
		while (element != null) {
			parents.add(element.getIdentifier());
			InstElement elt = element;
			if (elt instanceof InstPairwiseRelation)
				break;
			element = null;
			for (InstElement e : elt.getTargetRelations()) {
				if (((InstPairwiseRelation) e).getSupportMetaPairwiseRelIden()
						.equals("ExtendsRelation")) {
					element = e.getTargetRelations().get(0);
				}
			}
		}
		if (in)
			for (OpersIOAttribute ioAtt : inAttributes) {
				if (attribute.equals(ioAtt.getAttributeId())
						&& parents.contains(ioAtt.getConceptId()))
					if (ioAtt.isInclude()) {
						if (include > parents.indexOf(ioAtt.getConceptId()))
							include = parents.indexOf(ioAtt.getConceptId());
					} else if (exclude > parents.indexOf(ioAtt.getConceptId()))
						exclude = parents.indexOf(ioAtt.getConceptId());
			}
		if (!in)
			for (OpersIOAttribute ioAtt : outAttributes) {
				if (attribute.equals(ioAtt.getAttributeId())
						&& parents.contains(ioAtt.getConceptId()))
					if (ioAtt.isInclude()) {
						if (include > parents.indexOf(ioAtt.getConceptId()))
							include = parents.indexOf(ioAtt.getConceptId());
					} else if (exclude > parents.indexOf(ioAtt.getConceptId()))
						exclude = parents.indexOf(ioAtt.getConceptId());

			}

		if (include == 99999 && exclude == 99999)
			return 0;
		if (include < exclude)
			return 1;
		return -1;
	}

	@Override
	public int compareTo(Object o) {
		OpersSubOperation op = (OpersSubOperation) o;
		return this.getPosition() - op.getPosition();

	}
}
