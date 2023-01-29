package com.kodekonveyor.xmldoclet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.tools.Diagnostic.Kind;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class XmlDocletInitTest extends XmlDocletTestBase {

	@BeforeEach
	void setUp() {
		super.setUp();
		sut.init(locale, reporter);
	}

	@Test
	@DisplayName("Reports the locale")
	public void test4() {
		verify(reporter, times(1))
				.print(Kind.NOTE, XmlDocletTestData.DOCLET_USING_LOCALE_MY_LOCALE);
	}

	@Test
	@DisplayName("creates a new document with XmlHelper")
	public void test5() {
		verify(xmlHelper, times(1))
				.createDoc();
	}

	@Test
	@DisplayName("The created document is put to the doc field")
	public void test6() {
		assertEquals(document, sut.doc);
	}

	@Test
	@DisplayName("Creates an element of type 'doc'")
	public void test7() {
		verify(document, times(1)).createElement(XmlDocletConstants.DOC);
	}

	@Test
	@DisplayName("The created element is put to the rootElement field")
	public void test8() {
		assertEquals(rootElement, sut.rootElement);
	}

	@Test
	@DisplayName("The created element is put into the document")
	public void test9() {
		verify(document, times(1)).appendChild(rootElement);
	}

}
