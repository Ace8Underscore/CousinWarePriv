package io.ace.nordclient.command.commands;

import io.ace.nordclient.command.Command;
import io.ace.nordclient.managers.CommandManager;

public class ReloadSound extends Command {

    /**
     * @author Ace________/Ace_#1233
     */

    @Override
    public String[] getClientAlias() {
        return new String[]{"reloadsound", "reloadsounds", "soundreload"};
    }

    @Override
    public String getClientSyntax() {
        return "reloadsounds";
    }

    @Override
    public void onClientCommand(String command, String[] args) throws Exception {
        mc.getSoundHandler().sndManager.reloadSoundSystem();
    }

}