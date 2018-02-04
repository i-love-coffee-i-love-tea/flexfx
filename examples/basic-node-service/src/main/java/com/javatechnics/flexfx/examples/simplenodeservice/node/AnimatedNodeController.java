/*
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 *    Copyright Kerry Billingham - 2017
 *    @author Kerry Billingham
 */

package com.javatechnics.flexfx.examples.simplenodeservice.node;

import com.javatechnics.flexfx.examples.simplenodeservice.controls.ControlsCallback;
import com.javatechnics.flexfx.examples.simplenodeservice.scene.ShapeAnimator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.shape.Circle;

import java.util.Arrays;
import java.util.List;

/**
 * Controller for the AnimatedNode FXML layout.
 */
public class AnimatedNodeController
{
    static final String FXML_FILE = "AnimatedNode.fxml";

    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;

    List<Circle> circles;

    private ShapeAnimator circle1Animator;
    private ShapeAnimator circle2Animator;
    private ShapeAnimator circle3Animator;

    public void initialize()
    {
        circles = Arrays.asList(circle1, circle2, circle3);
        circle1.setEffect(new GaussianBlur());
        circle2.setEffect(new GaussianBlur());
        circle3.setEffect(new GaussianBlur());

        circle1Animator = new ShapeAnimator(circle1);
        circle2Animator = new ShapeAnimator(circle2);
        circle3Animator = new ShapeAnimator(circle3);

        circle1Animator.start();
        circle2Animator.start();
        circle3Animator.start();

    }

    public void destroy()
    {
        circle1Animator.stop();
        circle2Animator.stop();
        circle3Animator.stop();
    }

    public void setSpeed(final Double speed)
    {
        Platform.runLater(() ->
        {
            circle1Animator.setVelocity(speed);
            circle2Animator.setVelocity(speed);
            circle3Animator.setVelocity(speed);
        });
    }

    public void setEffect(final ControlsCallback.EffectType effectType)
    {
        Platform.runLater(() ->
        {
            for (final Circle circle : circles)
            {
                switch (effectType)
                {
                    case BLUR:
                        circle.setEffect(new GaussianBlur());
                        break;
                    case BLOOM:
                        circle.setEffect(new Bloom());
                        break;
                    case GLOW:
                        circle.setEffect(new Glow());
                        break;
                    case UNKNOWN:
                    default:
                }
            }
        });
    }
}
