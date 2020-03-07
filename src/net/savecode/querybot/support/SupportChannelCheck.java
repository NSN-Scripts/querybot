package net.savecode.querybot.support;

import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import net.savecode.querybot.QueryBot;

public class SupportChannelCheck {

    public static void SupportChannelCheck() {

        QueryBot.getTs3Api().registerAllEvents();

        QueryBot.getTs3Api().addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onClientMoved(ClientMovedEvent e) {
                Client client = QueryBot.getTs3Api().getClientInfo(e.getClientId());
                if (client.getChannelId() == QueryBot.SUPPORT_CHANNEL) {
                    QueryBot.getTs3Api().sendPrivateMessage(e.getClientId(), "\n" +
                            "══════ Support ══════\n" +
                            " \n" +
                            "» Willkommen " + client.getNickname() + " im Support Warteraum!\n" +
                            "Wähle einen Grund deines Aufenthalts:\n" +
                            " \n" +
                            "1. Allgemeine Fragen\n" +
                            "2. Technische schwierigkeiten\n" +
                            "3. Sonstiges\n" +
                            " \n" +
                            "══════ Willkommen ══════");
                }
            }
        });

    }

}