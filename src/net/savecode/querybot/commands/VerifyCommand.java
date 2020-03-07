package net.savecode.querybot.commands;

import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import net.savecode.querybot.QueryBot;

public class VerifyCommand {

    public static void VerifyCommand() {

        QueryBot.getTs3Api().registerAllEvents();

        QueryBot.getTs3Api().addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onTextMessage(TextMessageEvent e) {
                Client client = QueryBot.getTs3Api().getClientInfo(e.getInvokerId());

                if (e.getMessage().equalsIgnoreCase("!verify")) {
                    if (!client.isInServerGroup(QueryBot.VERIFY_GROUP)) {
                        QueryBot.getTs3Api().addClientToServerGroup(QueryBot.VERIFY_GROUP, client.getDatabaseId());
                        QueryBot.getTs3Api().sendPrivateMessage(client.getId(), "Du hast dich erfolgreich verifiziert!");
                    } else {
                        QueryBot.getTs3Api().sendPrivateMessage(client.getId(), "Du bist bereits verifiziert!");
                    }
                }

            }
        });

    }

}