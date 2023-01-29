package com.kodekonveyor.xmldoclet;

import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import jdk.javadoc.doclet.Doclet;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic.Kind;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;


public class XmlDoclet implements Doclet {
    Reporter reporter;
    Boolean doComments = false;
    org.w3c.dom.Element rootElement;
    Document doc;
    
    @Override
    public void init(Locale locale, Reporter reporter) {
        reporter.print(Kind.NOTE, "Doclet using locale: " + locale);
        this.reporter = reporter;
        
        DocumentBuilderFactory dbFactory =
                DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder;
				try {
					dBuilder = dbFactory.newDocumentBuilder();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
					return;
				}
                doc = dBuilder.newDocument();
                
                rootElement = doc.createElement("doc");
                doc.appendChild(rootElement);
    }

    private org.w3c.dom.Element printElement(org.w3c.dom.Element parent, Element e) {
         org.w3c.dom.Element element = doc.createElement(e.getKind().toString());
         element.setAttribute("name", e.getSimpleName().toString());
         element.setAttribute("fullName", e.toString());
         for( AnnotationMirror annotation: e.getAnnotationMirrors()) {
        	 org.w3c.dom.Element a = doc.createElement("annotation");
        	 element.appendChild(a);
        	 a.setAttribute("type", 
        	 annotation.getAnnotationType().toString());
        	 for(Entry<? extends ExecutableElement, ? extends AnnotationValue> param:
        		 	annotation.getElementValues().entrySet()) {
        		 org.w3c.dom.Element paramE = doc.createElement("param");
				a.appendChild(paramE);
				paramE.setAttribute("name", param.getKey().toString());
				paramE.setAttribute("value", param.getValue().toString());
        	 }
         }
        parent.appendChild(element);
        return element;
    }

    @Override
    public boolean run(DocletEnvironment docEnv) {
         org.w3c.dom.Element p = doc.createElement("specified");
         rootElement.appendChild(p);
        for (TypeElement t : ElementFilter.typesIn(docEnv.getSpecifiedElements())) {
            org.w3c.dom.Element te = printElement(p, t);
            for (Element e : t.getEnclosedElements()) {
                printElement(te, e);
            }        	 
         }
        org.w3c.dom.Element p2 = doc.createElement("included");
        rootElement.appendChild(p2);
        for (TypeElement t : ElementFilter.typesIn(docEnv.getIncludedElements())) {
            org.w3c.dom.Element te2 = printElement(p2, t);
            for (Element e : t.getEnclosedElements()) {
                printElement(te2, e);
            }
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new File("javadoc.xml"));
	        transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
			return false;
		}
        return true;
    }

    @Override
    public String getName() {
        return "Example";
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
 