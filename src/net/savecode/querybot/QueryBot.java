package net.savecode.querybot;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.exception.TS3ConnectionFailedException;
import net.savecode.querybot.commands.InfoCommand;
import net.savecode.querybot.commands.VerifyCommand;
import net.savecode.querybot.listeners.ClientJoinListener;
import net.savecode.querybot.support.SupportChannelCheck;
import net.savecode.querybot.support.SupportSelection;
import net.savecode.querybot.timer.Timer;

public class QueryBot {

    private static final TS3Config ts3Config = new TS3Config();
    private static final TS3Query ts3Query = new TS3Query(ts3Config);
    private static final TS3Api ts3Api = ts3Query.getApi();

    private static String HOST = "localhost"; //IP Adresse des TeamSpeaks
    private static int PORT = 9987; //Port des TeamSpeaks (meist 9987)
    private static String QUERY_USERNAME = "serveradmin"; //Username der Query
    private static String QUERY_PASSWORD = "VecBqEHR"; //Passwort der Query
    private static String QUERY_NICKNAME = "LiveStream | Query"; //Nickname der Query

    public static final int VERIFY_GROUP = 9; //TeamSpeak ID von der TeamSpeak Gruppe, um sich zu verifizieren
    public static final int SUPPORT_CHANNEL = 2; //Support Channel
    public static final int SUPPORT_NOTIFY = 11; //Der Rang, wo Supporter über den Aufenthalt der User im Support Benachrichtigt werden.
    public static final int TEAM_AFK = 4; //ID des Team AFK Channels (dort werden keine User Benachrichtigt)

    public static void main(String[] args) {
        try {
            ts3Config.setHost(HOST);
            ts3Query.connect();
            ts3Api.login(QUERY_USERNAME, QUERY_PASSWORD);
            ts3Api.selectVirtualServerByPort(PORT);
            ts3Api.setNickname(QUERY_NICKNAME);

            regListeners();
            regCommands();
            support();

            System.out.println("Der QueryBot konnte sich auf den TeamSpeak " + HOST + " erfolgreich verbinden!");
        } catch (TS3ConnectionFailedException e) {
            System.out.println("Der QueryBot konnte sich nicht auf den TeamSpeak " + HOST + " verbinden!");
        }
    }

    private static void regListeners() {
        ClientJoinListener.ClientJoinListener();
    }

    private static void regCommands() {
        VerifyCommand.VerifyCommand();
        InfoCommand.InfoCommand();
    }

    private static void support() {
        SupportChannelCheck.SupportChannelCheck();
        SupportSelection.SupportSelection();
        Timer.startCheck();
    }


    public static TS3Api getTs3Api() {
        return ts3Api;
    }

    public static TS3Query getTs3Query() {
        return ts3Query;
    }

    public static TS3Config getTs3Config() {
        return ts3Config;
    }
}