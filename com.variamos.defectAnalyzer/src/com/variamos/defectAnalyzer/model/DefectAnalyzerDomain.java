package com.variamos.defectAnalyzer.model;

import java.util.List;

import com.variamos.hlcl.Domain;

public interface DefectAnalyzerDomain extends Domain {

	public List<Integer> getDomainValues();
	
	
}
