package com.kodekonveyor.xmldoclet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.lang.model.SourceVersion;
import javax.tools.Diagnostic.Kind;

import org.w3c.dom.Document;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;

public class XmlDoclet implements Doclet {

	Reporter reporter;
	Boolean doComments = false;
	org.w3c.dom.Element rootElement;
	Document doc;
	XmlHelper helper;
	ElementDocumenter elementDocumenter;

	public XmlDoclet() {
		helper = new XmlHelper();
		elementDocumenter = new ElementDocumenter();
	}

	XmlDoclet(XmlHelper helper, ElementDocumenter elem) {
		this.helper = helper;
	}

	@Override
	public void init(Locale locale, Reporter reporter) {
		reporter.print(Kind.NOTE, XmlDocletConstants.DOCLET_USING_LOCALE + locale);
		this.reporter = reporter;

		doc = helper.createDoc();

		rootElement = doc.createElement(XmlDocletConstants.DOC);
		doc.appendChild(rootElement);
	}

	@Override
	public boolean run(DocletEnvironment docEnv) {
		//		for (TypeElement type : ElementFilter
		//				.typesIn(docEnv.getIncludedElements())) {
		//		}
		/*
				org.w3c.dom.Element domFromType = elementDocumenter.documentElement(doc,
						rootElement, type);
				for (Element subElement : type.getEnclosedElements()) {
					elementDocumenter.documentElement(doc, domFromType, subElement);
				}
			}
			*/
		return helper.save(doc);
	}

	@Override
	public String getName() {
		return XmlDocletConstants.DOCLET_IDENTIFIER;
	}

	@Override
	public Set<? extends Option> getSupportedOptions() {
		Option[] options = {
		};
		return new HashSet<>(Arrays.asList(options));
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latest();
	}
}
