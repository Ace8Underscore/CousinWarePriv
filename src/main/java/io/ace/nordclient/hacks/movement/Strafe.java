package io.ace.nordclient.hacks.movement;

import io.ace.nordclient.CousinWare;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.utilz.MathUtil;
import io.ace.nordclient.utilz.Setting;

import java.util.ArrayList;

public class Strafe extends Hack {

    int delay = 0;
    Setting speedMode;
    Setting speed;

    public Strafe() {
        super("Speed", Category.MOVEMENT);

        ArrayList<String> speedModes = new ArrayList<>();
        speedModes.add("Launch");
        speedModes.add("Strafe");
        speedModes.add("StrafeAcel");


        CousinWare.INSTANCE.settingsManager.rSetting(speedMode = new Setting("Mode", this,"Launch" ,speedModes, "StrafeMode"));
        CousinWare.INSTANCE.settingsManager.rSetting(speed = new Setting("Speed", this, .36, 0, .4, false, "StrafeSpeed"));

    }

    @Override
    public void onUpdate() {
        delay++;
        mc.player.speedInAir = .021f;
        mc.player.setSprinting(true);
        if (speedMode.getValString().equalsIgnoreCase("launch")) doSpeedLaunch();
        if (speedMode.getValString().equalsIgnoreCase("strafe")) doSpeedStrafe();
        if (speedMode.getValString().equalsIgnoreCase("strafeacel")) doSpeedStafeAcel();


    }

    public void doSpeedLaunch() {
        mc.player.motionY *= 1;
        if (mc.player.onGround) {
            if (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown()) {
                mc.player.jump();
                final double[] dir = MathUtil.directionSpeed(.43);
                mc.player.motionX = dir[0];
                mc.player.motionZ = dir[1];
            }
        }
    }

    public void doSpeedStrafe() {
        mc.player.motionY *= 1;
        if (mc.player.onGround) {
            if (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown()) {
                mc.player.jump();
                final double[] dir = MathUtil.directionSpeed(.26);
                mc.player.motionX = dir[0];
                mc.player.motionZ = dir[1];
            }
        } else {
                final double[] dir = MathUtil.directionSpeed(.26);
                mc.player.motionX = dir[0];
                mc.player.motionZ = dir[1];
            }

        }

        public void doSpeedStafeAcel() {
            mc.player.motionY *= 1;
            if (mc.player.onGround) {
                if (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown()) {
                    mc.player.jump();
                    final double[] dir = MathUtil.directionSpeed(.26);
                    mc.player.motionX = dir[0];
                    mc.player.motionZ = dir[1];
                }
            } else {
                if (delay == 0 || delay == 1 || delay == 2 || delay == 3) {
                    final double[] dir = MathUtil.directionSpeed(.26);
                    mc.player.motionX = dir[0];
                    mc.player.motionZ = dir[1];
                }
                if (delay >= 4) {
                    final double[] dir = MathUtil.directionSpeed(speed.getValDouble());
                    mc.player.motionX = dir[0];
                    mc.player.motionZ = dir[1];
                    delay = 0;
                }
            }
        }




}