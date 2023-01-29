package com.kodekonveyor.xmldoclet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jdk.javadoc.doclet.DocletEnvironment;

public class XmlDocletRunTest extends XmlDocletTestBase {

	private DocletEnvironment docletEnvironment;
	private boolean returned;

	@BeforeEach
	void setUp() {
		super.setUp();
		sut.doc = document;
	}

	@Test
	@DisplayName("returns true if the save is successful")
	public void test1() {
		when(xmlHelper.save(document)).thenReturn(true);
		returned = sut.run(docletEnvironment);
		assertEquals(true, returned);
	}

	@Test
	@DisplayName("returns false if the save is not successful")
	public void test2() {
		when(xmlHelper.save(document)).thenReturn(false);
		returned = sut.run(docletEnvironment);
		assertEquals(false, returned);
	}

	@Test
	@DisplayName("obtains included elements from doclet environment")
	public void test3() {

		returned = sut.run(docletEnvironment);
		verify(docletEnvironment).getIncludedElements();
	}

}
