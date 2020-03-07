package net.savecode.querybot.listeners;

import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import net.savecode.querybot.QueryBot;

public class ClientJoinListener {

    public static void ClientJoinListener() {

        QueryBot.getTs3Api().registerAllEvents();

        QueryBot.getTs3Api().addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onClientJoin(ClientJoinEvent e) {
                Client client = QueryBot.getTs3Api().getClientInfo(e.getClientId());
                if (!client.isInServerGroup(QueryBot.VERIFY_GROUP)) {
                    QueryBot.getTs3Api().sendPrivateMessage(e.getClientId(), "\n" +
                            "══════ Willkommen ══════\n" +
                            " \n" +
                            "» Willkommen " + client.getNickname() + " auf unserem TeamSpeak!\n" +
                            "Bitte nutze [B]!verify[/B], um dich zu verifizieren.\n" +
                            " \n" +
                            "══════ Willkommen ══════");
                } else {
                    QueryBot.getTs3Api().sendPrivateMessage(e.getClientId(), "\n" +
                            "══════ Willkommen ══════\n" +
                            " \n" +
                            "» Willkommen " + client.getNickname() + " auf unserem TeamSpeak!\n" +
                            "Falls du Probleme hast, so melde dich gerne in unserem Support!\n" +
                            " \n" +
                            "Liebe Grüße\n" +
                            "das Serverteam" +
                            " \n" +
                            "══════ Willkommen ══════");
                }
            }
        });

    }

}
