package net.savecode.querybot.commands;

import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import net.savecode.querybot.QueryBot;
import net.savecode.querybot.timer.Timer;

public class InfoCommand {

    public static void InfoCommand() {

        QueryBot.getTs3Api().registerAllEvents();

        QueryBot.getTs3Api().addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onTextMessage(TextMessageEvent e) {
                Client client = QueryBot.getTs3Api().getClientInfo(e.getInvokerId());
                if (e.getMessage().equalsIgnoreCase("!info")) {
                    QueryBot.getTs3Api().sendPrivateMessage(e.getInvokerId(), "\n" +
                            "Informationen zu unserem TeamSpeak-Server\n" +
                            " \n" +
                            "• Aktuelle User online:" + QueryBot.getTs3Api().getClients().size() + "\n" +
                            "• Aktuelle Supporter online:" + Timer.supporter.size() + "\n" +
                            " \n" +
                            "Wichtige Commands findest du unter [B]!help[/B]");
                }
            }
        });

    }

}
