package com.kodekonveyor.xmldoclet;

import java.util.Map.Entry;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

import org.w3c.dom.Document;

public class ElementDocumenter {

	public org.w3c.dom.Element documentElement(Document doc,
			org.w3c.dom.Element parent,
			Element astElement) {
		org.w3c.dom.Element element = doc
				.createElement(astElement.getKind().toString());
		element.setAttribute(XmlDocletConstants.ANNOTATION_PARAMETER_NAME_ATTRIBUTE,
				astElement.getSimpleName().toString());
		element.setAttribute(XmlDocletConstants.FULL_NAME, astElement.toString());
		for (AnnotationMirror annotation : astElement.getAnnotationMirrors()) {
			org.w3c.dom.Element annotationElement = doc
					.createElement(XmlDocletConstants.ANNOTATION);
			element.appendChild(annotationElement);
			annotationElement.setAttribute(XmlDocletConstants.TYPE,
					annotation.getAnnotationType().toString());
			for (Entry<? extends ExecutableElement, ? extends AnnotationValue> param : annotation
					.getElementValues().entrySet()) {
				org.w3c.dom.Element paramElement = doc
						.createElement(XmlDocletConstants.PARAM);
				annotationElement.appendChild(paramElement);
				paramElement.setAttribute(
						XmlDocletConstants.ANNOTATION_PARAMETER_NAME_ATTRIBUTE,
						param.getKey().toString());
				paramElement.setAttribute(
						XmlDocletConstants.ANNOTATION_PARAMETER_VALUE_ATTRIBUTE,
						param.getValue().toString());

			}
		}
		parent.appendChild(element);
		return element;
	}

}
