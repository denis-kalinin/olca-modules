
package org.openlca.ilcd.processes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.annotations.FreeText;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationOfOperationSupplyOrProductionType", propOrder = {
		"description",
		"other"
})
public class Location implements Serializable {

	private final static long serialVersionUID = 1L;

	@FreeText
	@XmlElement(name = "descriptionOfRestrictions")
	public final List<LangString> description = new ArrayList<>();

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAttribute(name = "location")
	public String location;

	@XmlAttribute(name = "latitudeAndLongitude")
	public String latitudeAndLongitude;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

}
