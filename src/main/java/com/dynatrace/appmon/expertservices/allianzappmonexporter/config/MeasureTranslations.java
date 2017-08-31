package com.dynatrace.appmon.expertservices.allianzappmonexporter.config;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="translations")
public class MeasureTranslations {
	private ArrayList<MeasureTranslation> measureTranslations;

	public ArrayList<MeasureTranslation> getMeasureTranslations() {
		return measureTranslations;
	}
	@XmlElement(name = "measuretranslation")
	public void setMeasureTranslations(ArrayList<MeasureTranslation> measureTranslations) {
		this.measureTranslations = measureTranslations;
	}
	
	

}
