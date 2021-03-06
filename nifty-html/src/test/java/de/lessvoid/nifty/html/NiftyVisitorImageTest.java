package de.lessvoid.nifty.html;

import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import org.htmlparser.tags.BodyTag;
import org.htmlparser.tags.ImageTag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

public class NiftyVisitorImageTest {
  private NiftyVisitor visitor;
  private NiftyBuilderFactory builderFactoryMock;

  @Before
  public void before() {
    builderFactoryMock = createMock(NiftyBuilderFactory.class);
    visitor = new NiftyVisitor(null, builderFactoryMock, null, null);
  }

  @After
  public void after() {
    verify(builderFactoryMock);
  }

  @Test
  public void simpleBodyWithBasicImageSuccess() throws Exception {
    PanelBuilder bodyPanelBuilder = new PanelBuilder();
    ImageBuilder imageBuilder = new ImageBuilder();

    expect(builderFactoryMock.createBodyPanelBuilder()).andReturn(bodyPanelBuilder);
    expect(builderFactoryMock.createImageBuilder(eq("src"), (String)isNull(), (String)isNull(), (String)isNull(), (String)isNull(), (String)isNull())).andReturn(imageBuilder);
    replay(builderFactoryMock);

    BodyTag bodyTag = new BodyTag();
    visitor.visitTag(bodyTag);

      // add image
      ImageTag imageTag = new ImageTag();
      imageTag.setAttribute("src", "src");
      visitor.visitTag(imageTag);
      visitor.visitEndTag(imageTag);

    // close body
    visitor.visitEndTag(bodyTag);

    assertEquals(bodyPanelBuilder, visitor.builder());
    assertEquals(1, bodyPanelBuilder.getElementBuilders().size());
    assertEquals(imageBuilder, bodyPanelBuilder.getElementBuilders().get(0));
  }
}
