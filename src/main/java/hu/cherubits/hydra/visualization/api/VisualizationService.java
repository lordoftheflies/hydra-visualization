package hu.cherubits.hydra.visualization.api;

import jdk.internal.util.xml.impl.Input;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by lordoftheflies on 2017.03.08..
 */
public interface VisualizationService<InputType extends Serializable, OutputType extends Serializable> {
    OutputType plot2d(InputType data, Map<String, Object> properties);
    OutputType plot2dDelta(InputType data, Map<String, Object> properties);
    OutputType plot3d(InputType data, Map<String, Object> properties);
    OutputType plot3dDelta(InputType data, Map<String, Object> properties);
}
