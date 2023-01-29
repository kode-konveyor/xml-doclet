package com.kodekonveyor.xmldoclet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import jdk.javadoc.doclet.Reporter;

public class XmlDocletTestBase {

	protected XmlDoclet sut;
	protected Reporter reporter;
	protected Locale locale;
	protected XmlHelper xmlHelper;
	protected Document document;
	Element rootElement;
	private ElementDocumenter elementDocumenter;

	public XmlDocletTestBase() {
		super();
	}

	@BeforeEach
	void setUp() {
		reporter = mock(Reporter.class);
		locale = mock(Locale.class);
		when(locale.toString()).thenReturn(XmlDocletTestData.MY_LOCALE);
		xmlHelper = mock(XmlHelper.class);
		elementDocumenter = mock(ElementDocumenter.class);
		document = mock(Document.class);
		when(xmlHelper.createDoc()).thenReturn(document);
		rootElement = mock(Element.class);
		when(document.createElement(XmlDocletConstants.DOC))
				.thenReturn(rootElement);
		sut = new XmlDoclet(xmlHelper, elementDocumenter);
	}

}