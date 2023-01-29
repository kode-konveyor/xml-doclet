package com.kodekonveyor.xmldoclet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.type.DeclaredType;

import org.junit.jupiter.api.BeforeEach;
import org.w3c.dom.Document;

public class ElementDocumenterTestBase {

	protected Element element;
	protected org.w3c.dom.Element methodElement;
	protected org.w3c.dom.Element returnValue;
	@SuppressWarnings("rawtypes")
	private List annotations = new ArrayList();
	protected AnnotationMirror annotation;
	protected org.w3c.dom.Element annotationElement;
	private DeclaredType annotationType;
	@SuppressWarnings("rawtypes")
	private Map annotationValueMap = new HashMap<>();
	protected org.w3c.dom.Element paramElement;
	private ExecutableElement annotationParamName;
	private AnnotationValue annotationParamValue;
	protected Document document;
	protected org.w3c.dom.Element rootElement;

	public ElementDocumenterTestBase() {
		super();
	}

	@SuppressWarnings("unchecked")
	@BeforeEach
	void setUp() {
		document = mock(Document.class);
		rootElement = mock(org.w3c.dom.Element.class);

		element = mock(Element.class);
		when(element.getKind()).thenReturn(ElementKind.METHOD);
		annotation = mock(AnnotationMirror.class);
		annotationParamName = mock(ExecutableElement.class);
		when(annotationParamName.toString())
				.thenReturn(XmlDocletTestData.ANNOTATION_PARAM_NAME);
		annotationParamValue = mock(AnnotationValue.class);
		when(annotationParamValue.toString())
				.thenReturn(XmlDocletTestData.ANNOTATION_PARAM_VALUE);
		annotationValueMap.put(annotationParamName, annotationParamValue);
		when(annotation.getElementValues()).thenReturn(annotationValueMap);
		annotationType = mock(DeclaredType.class);
		when(annotation.getAnnotationType()).thenReturn(annotationType);
		when(annotationType.toString())
				.thenReturn(XmlDocletTestData.ANNOTATION_TYPE);
		annotations.add(annotation);
		when(element.getAnnotationMirrors()).thenReturn(annotations);
		Name name = mock(Name.class);
		when(name.toString()).thenReturn(XmlDocletTestData.ELEMENT_NAME);
		when(element.getSimpleName()).thenReturn(name);
		when(element.toString())
				.thenReturn(XmlDocletTestData.FULL_NAME_OF_THE_ELEMENT);
		methodElement = mock(org.w3c.dom.Element.class);
		when(document.createElement(ElementKind.METHOD.toString()))
				.thenReturn(methodElement);
		annotationElement = mock(org.w3c.dom.Element.class);
		when(document.createElement(XmlDocletConstants.ANNOTATION))
				.thenReturn(annotationElement);
		paramElement = mock(org.w3c.dom.Element.class);
		when(document.createElement(XmlDocletConstants.PARAM))
				.thenReturn(paramElement);

		returnValue = new ElementDocumenter().documentElement(document, rootElement,
				element);
	}

}