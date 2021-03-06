package de.lessvoid.xml.lwxs.elements;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.lessvoid.xml.lwxs.XmlType;
import de.lessvoid.xml.xpp3.XmlParser;

public class XmlProcessorElementTest {
  private XmlParser xmlParserMock;
  private XmlProcessorType xmlElementProcessorMock;

  @Before
  public void setUp() throws Exception {
    xmlParserMock = createMock(XmlParser.class);
    xmlElementProcessorMock = createMock(XmlProcessorType.class);
  }

  @After
  public void tearDown() {
    verify(xmlParserMock);
    verify(xmlElementProcessorMock);
  }

  @Test
  public void testOccuresRequired() throws Exception {
    xmlParserMock.required("name", xmlElementProcessorMock);
    replay(xmlParserMock);

    XmlType parent = createMock(XmlType.class);
    replay(parent);

    xmlElementProcessorMock.parentLinkSet(parent, "name");
    replay(xmlElementProcessorMock);

    XmlProcessorElement child = new XmlProcessorElement(xmlElementProcessorMock, "name", OccursEnum.required);
    child.process(xmlParserMock, parent);

    verify(parent);
  }

  @Test
  public void testOccuresOneOrMore() throws Exception {
    xmlParserMock.oneOrMore("name", xmlElementProcessorMock);
    replay(xmlParserMock);

    XmlType parent = createMock(XmlType.class);
    replay(parent);

    xmlElementProcessorMock.parentLinkAdd(parent, "name");
    replay(xmlElementProcessorMock);

    XmlProcessorElement child = new XmlProcessorElement(xmlElementProcessorMock, "name", OccursEnum.oneOrMore);
    child.process(xmlParserMock, parent);

    verify(parent);
  }

  @Test
  public void testOccuresOptional() throws Exception {
    xmlParserMock.optional("name", xmlElementProcessorMock);
    replay(xmlParserMock);

    XmlType parent = createMock(XmlType.class);
    replay(parent);

    xmlElementProcessorMock.parentLinkSet(parent, "name");
    replay(xmlElementProcessorMock);

    XmlProcessorElement child = new XmlProcessorElement(xmlElementProcessorMock, "name", OccursEnum.optional);
    child.process(xmlParserMock, parent);

    verify(parent);
  }

  @Test
  public void testOccuresZeroOrMore() throws Exception {
    xmlParserMock.zeroOrMore("name", xmlElementProcessorMock);
    replay(xmlParserMock);

    XmlType parent = createMock(XmlType.class);
    replay(parent);

    xmlElementProcessorMock.parentLinkAdd(parent, "name");
    replay(xmlElementProcessorMock);

    XmlProcessorElement child = new XmlProcessorElement(xmlElementProcessorMock, "name", OccursEnum.zeroOrMore);
    child.process(xmlParserMock, parent);

    verify(parent);
  }
}
