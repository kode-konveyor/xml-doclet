package com.kodekonveyor.xmldoclet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class XmlDocletAuxiliaryFunctionsTest extends XmlDocletTestBase {

	@Test
	@DisplayName("the name of the doclet is its groupid and artifactid")
	public void test1() {
		assertEquals(XmlDocletConstants.DOCLET_IDENTIFIER, sut.getName());
	}

	@Test
	@DisplayName("No supported options (yet)")
	public void test2() {
		assertEquals(0, sut.getSupportedOptions().size());
	}

	@Test
	@DisplayName("Supports Java source version 19")
	public void test3() {
		assertEquals(XmlDocletTestData.RELEASE_19,
				sut.getSupportedSourceVersion().toString());
	}

}
