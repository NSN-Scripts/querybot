package net.savecode.querybot.support;

import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import net.savecode.querybot.QueryBot;

import java.util.HashMap;
import java.util.Map;

public class SupportSelection {

    public static void SupportSelection() {

        QueryBot.getTs3Api().registerAllEvents();

        QueryBot.getTs3Api().addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onTextMessage(TextMessageEvent e) {
                Client client = QueryBot.getTs3Api().getClientInfo(e.getInvokerId());
                if (client.getChannelId() == QueryBot.SUPPORT_CHANNEL) {
                    if (e.getMessage().equalsIgnoreCase("1")) {
                        QueryBot.getTs3Api().sendPrivateMessage(e.getInvokerId(), "Du hast den Supportgrund [B]1 - Allgemein[/B] ausgewählt.");
                        QueryBot.getTs3Api().sendPrivateMessage(e.getInvokerId(), "Ein Supporter wird nun verständigt!");
                        for (Client clients : QueryBot.getTs3Api().getClients()) {
                            if (clients.isInServerGroup(QueryBot.SUPPORT_NOTIFY)) {
                                QueryBot.getTs3Api().pokeClient(clients.getId(), "Der User [B]" + client.getNickname() + " [/B] wartet im Support. Grund: [B]1 - Allgemein [/B]");
                            }
                        }
                    }
                    if (e.getMessage().equalsIgnoreCase("2")) {
                        QueryBot.getTs3Api().sendPrivateMessage(e.getInvokerId(), "Du hast den Supportgrund [B]2 - Technische Schwierigkeiten[/B] ausgewählt.");
                        QueryBot.getTs3Api().sendPrivateMessage(e.getInvokerId(), "Ein Supporter wird nun verständigt!");
                        for (Client clients : QueryBot.getTs3Api().getClients()) {
                            if (clients.isInServerGroup(QueryBot.SUPPORT_NOTIFY)) {
                                QueryBot.getTs3Api().pokeClient(clients.getId(), "Der User [B]" + client.getNickname() + " [/B] wartet im Support. Grund: [B]2 - Technische Schwierigkeiten [/B]");
                            }
                        }
                    }
                    if (e.getMessage().equalsIgnoreCase("3")) {
                        QueryBot.getTs3Api().sendPrivateMessage(e.getInvokerId(), "Du hast den Supportgrund [B]3 - Sonstiges [/B] ausgewählt.");
                        QueryBot.getTs3Api().sendPrivateMessage(e.getInvokerId(), "Ein Supporter wird nun verständigt!");
                        for (Client clients : QueryBot.getTs3Api().getClients()) {
                            if (clients.isInServerGroup(QueryBot.SUPPORT_NOTIFY)) {
                                QueryBot.getTs3Api().pokeClient(clients.getId(), "Der User [B]" + client.getNickname() + " [/B] wartet im Support. Grund: [B]3 - Sonstiges [/B]");
                            }
                        }
                    }
                }
            }
        });

    }

}
