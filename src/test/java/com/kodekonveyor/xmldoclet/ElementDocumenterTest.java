package com.kodekonveyor.xmldoclet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ElementDocumenterTest extends ElementDocumenterTestBase {

	@Test
	@DisplayName("return a dom element named after the kind of documented AST element")
	public void test1() {
		assertEquals(methodElement, returnValue);
	}

	@Test
	@DisplayName("the name attribute of the dom element is set to the simple name of the AST element")
	public void test2() {
		verify(methodElement).setAttribute(
				XmlDocletConstants.ANNOTATION_PARAMETER_NAME_ATTRIBUTE,
				XmlDocletTestData.ELEMENT_NAME);
	}

	@Test
	@DisplayName("the fullName attribute of the dom element is set to the full name of the AST element")
	public void test3() {
		verify(methodElement).setAttribute(XmlDocletConstants.FULL_NAME,
				XmlDocletTestData.FULL_NAME_OF_THE_ELEMENT);
	}

	@Test
	@DisplayName("The annotations of the AST element are obtained")
	public void test4() {
		verify(element).getAnnotationMirrors();
	}

	@Test
	@DisplayName("An annotation dom element is created for each annotations")
	public void test5() {
		verify(document, times(1)).createElement(XmlDocletConstants.ANNOTATION);
	}

	@Test
	@DisplayName("The annotation dom element is appended to the returned element")
	public void test6() {
		verify(methodElement, times(1)).appendChild(annotationElement);
	}

	@Test
	@DisplayName("The type of the annotation is set as the type of the annotation in the AST")
	public void test7() {
		verify(annotationElement).setAttribute(XmlDocletConstants.TYPE,
				XmlDocletTestData.ANNOTATION_TYPE);
	}

	@Test
	@DisplayName("The annotation parameters of the AST annotation are obtained")
	public void test8() {
		verify(annotation).getElementValues();
	}

	@Test
	@DisplayName("For each annotation parameter a param dom element is created")
	public void test9() {
		verify(document).createElement(XmlDocletConstants.PARAM);
	}

	@Test
	@DisplayName("The param dom element is added to the annotation")
	public void test10() {
		verify(annotationElement).appendChild(paramElement);
	}

	@Test
	@DisplayName("The name attribute of the param dom element is set to the annotation key")
	public void test11() {
		verify(paramElement).setAttribute(
				XmlDocletConstants.ANNOTATION_PARAMETER_NAME_ATTRIBUTE,
				XmlDocletTestData.ANNOTATION_PARAM_NAME);
	}

	@Test
	@DisplayName("The value attribute of the param dom element is set to the annotation key")
	public void test12() {
		verify(paramElement).setAttribute(
				XmlDocletConstants.ANNOTATION_PARAMETER_VALUE_ATTRIBUTE,
				XmlDocletTestData.ANNOTATION_PARAM_VALUE);
	}

	@Test
	@DisplayName("The dom representation of the AST element is appended to the parent")
	public void test13() {
		verify(rootElement).appendChild(methodElement);
	}

}
