package hu.chrubits.hydra.js;

import hu.cherubits.hydra.js.RinoTemplate;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lordoftheflies on 2017.03.08..
 */
public class RhinoTemplateTest {

    private RinoTemplate template = new RinoTemplate();

    @Test
    public void testCostruction() {
        RinoTemplate template = new RinoTemplate();
        Assert.assertNotNull(template);
    }

    @Test
    public void testMultipleCostruction() {

        RinoTemplate template = new RinoTemplate();
        RinoTemplate template2 = new RinoTemplate();

        Assert.assertNotNull(template);
        Assert.assertNotNull(template2);
    }

    public void testImportingVisualuzationJs() {
        RinoTemplate template = new RinoTemplate();


        Map<String, Object> properties = new HashMap<>();
        properties.put("width", 640);
        properties.put("height", 480);
        properties.put("frequency_span", 40);
        properties.put("frequency_center", 100);
        properties.put("amplitude_span", -100);
        properties.put("amplitude_center", 0);

        template.visualize(() -> Arrays.stream(new Integer[][]{
                new Integer[]{0, 0},
                new Integer[]{0, 1},
        }), properties);

    }


}
