package net.savecode.querybot.timer;

import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import net.savecode.querybot.QueryBot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class Timer {

    private static final int SPACER_SUPPORT_AVAILABLE = 5;

    public static ArrayList<Integer> supporter = new ArrayList();

    public static void startCheck() {
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                supporter.clear();
                for (Client client : QueryBot.getTs3Api().getClients()) {
                    if (!(client.isServerQueryClient())) {
                        if (!(client.getChannelId() == QueryBot.TEAM_AFK)) {
                            if (client.isInServerGroup(QueryBot.SUPPORT_NOTIFY)) {
                                supporter.add(client.getId());
                            }
                        }
                    }
                }
                Map<ChannelProperty, String> property;
                if (supporter.size() == 0) {
                    if (!(QueryBot.getTs3Api().getChannelInfo(SPACER_SUPPORT_AVAILABLE).getName().contains("[cspacer]Supporter Online: Keine!"))) {
                        property = new HashMap<ChannelProperty, String>();
                        property.put(ChannelProperty.CHANNEL_NAME, "[cspacer]Supporter Online: Keine!");
                        property.put(ChannelProperty.CHANNEL_MAXCLIENTS, "0");
                        property.put(ChannelProperty.CHANNEL_FLAG_MAXCLIENTS_UNLIMITED, "0");
                        QueryBot.getTs3Api().editChannel(SPACER_SUPPORT_AVAILABLE, property);
                    }
                    if (!(QueryBot.getTs3Api().getChannelInfo(QueryBot.SUPPORT_CHANNEL).getName().contains("Support-Warteraum: Closed"))) {
                        property = new HashMap<ChannelProperty, String>();
                        property.put(ChannelProperty.CHANNEL_NAME, "Support-Warteraum: Closed");
                        property.put(ChannelProperty.CHANNEL_MAXCLIENTS, "0");
                        property.put(ChannelProperty.CHANNEL_FLAG_MAXCLIENTS_UNLIMITED, "0");
                        QueryBot.getTs3Api().editChannel(QueryBot.SUPPORT_CHANNEL, property);
                    }
                    for (Client clients : QueryBot.getTs3Api().getClients()) {
                        if (clients.getChannelId() == QueryBot.SUPPORT_CHANNEL) {
                            QueryBot.getTs3Api().kickClientFromChannel("", clients);
                            QueryBot.getTs3Api().pokeClient(clients.getId(), "Derzeit ist kein mehr Supporter da. Bitte probiere es später erneut.");
                        }
                    }
                } else {
                    if (!(QueryBot.getTs3Api().getChannelInfo(SPACER_SUPPORT_AVAILABLE).getName().contains("[cspacer]Supporter Online: " + supporter.size() + ""))) {
                        property = new HashMap<ChannelProperty, String>();

                        property.put(ChannelProperty.CHANNEL_NAME, "[cspacer]Supporter Online: " + supporter.size() + "");
                        property.put(ChannelProperty.CHANNEL_MAXCLIENTS, "0");
                        property.put(ChannelProperty.CHANNEL_FLAG_MAXCLIENTS_UNLIMITED, "0");
                        QueryBot.getTs3Api().editChannel(SPACER_SUPPORT_AVAILABLE, property);
                    }
                    if (!(QueryBot.getTs3Api().getChannelInfo(QueryBot.SUPPORT_CHANNEL).getName().contains("Support-Warteraum: Offen"))) {
                        property = new HashMap<ChannelProperty, String>();

                        property.put(ChannelProperty.CHANNEL_NAME, "Support-Warteraum: Offen");
                        property.put(ChannelProperty.CHANNEL_MAXCLIENTS, "5");
                        property.put(ChannelProperty.CHANNEL_FLAG_MAXCLIENTS_UNLIMITED, "0");
                        QueryBot.getTs3Api().editChannel(QueryBot.SUPPORT_CHANNEL, property);
                    }
                }
            }
        }, 1000, 5 * 1000);
    }
}