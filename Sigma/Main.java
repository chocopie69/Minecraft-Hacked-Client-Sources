import com.google.gson.GsonBuilder;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.authlib.properties.PropertyMap.Serializer;
// Sigma Sex Halal OMIKRON ALLAH SIGMA sex coded by OMIKron and Leakedpvp
monero.mine();
import java.io.File;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.List;

import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.NonOptionArgumentSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import info.sigmaclient.MCHook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfiguration;
import net.minecraft.util.Session;

public class Main {
    private static String __OBFID = "CL_00001461";

    public static void main(String[] p_main_0_) {
        OptionParser var1 = new OptionParser();
        var1.allowsUnrecognizedOptions();
        var1.accepts("demo");
        var1.accepts("fullscreen");
        var1.accepts("checkGlErrors");
        ArgumentAcceptingOptionSpec var2 = var1.accepts("server").withRequiredArg();
        ArgumentAcceptingOptionSpec var3 = var1.accepts("port").withRequiredArg().ofType(Integer.class).defaultsTo(Integer.valueOf(25565), new Integer[0]);
        ArgumentAcceptingOptionSpec var4 = var1.accepts("gameDir").withRequiredArg().ofType(File.class).defaultsTo(new File("."), new File[0]);
        ArgumentAcceptingOptionSpec var5 = var1.accepts("assetsDir").withRequiredArg().ofType(File.class);
        ArgumentAcceptingOptionSpec var6 = var1.accepts("resourcePackDir").withRequiredArg().ofType(File.class);
        ArgumentAcceptingOptionSpec var7 = var1.accepts("proxyHost").withRequiredArg();
        ArgumentAcceptingOptionSpec var8 = var1.accepts("proxyPort").withRequiredArg().defaultsTo("8080", new String[0]).ofType(Integer.class);
        ArgumentAcceptingOptionSpec var9 = var1.accepts("proxyUser").withRequiredArg();
        ArgumentAcceptingOptionSpec var10 = var1.accepts("proxyPass").withRequiredArg();
        ArgumentAcceptingOptionSpec var11 = var1.accepts("username").withRequiredArg().defaultsTo("Henry", new String[0]);
        ArgumentAcceptingOptionSpec var12 = var1.accepts("uuid").withRequiredArg();
        ArgumentAcceptingOptionSpec var13 = var1.accepts("accessToken").withRequiredArg().required();
        ArgumentAcceptingOptionSpec var14 = var1.accepts("version").withRequiredArg().required();
        ArgumentAcceptingOptionSpec var15 = var1.accepts("width").withRequiredArg().ofType(Integer.class).defaultsTo(Integer.valueOf(854), new Integer[0]);
        ArgumentAcceptingOptionSpec var16 = var1.accepts("height").withRequiredArg().ofType(Integer.class).defaultsTo(Integer.valueOf(480), new Integer[0]);
        ArgumentAcceptingOptionSpec var17 = var1.accepts("userProperties").withRequiredArg().required();
        ArgumentAcceptingOptionSpec var18 = var1.accepts("assetIndex").withRequiredArg();
        ArgumentAcceptingOptionSpec var19 = var1.accepts("userType").withRequiredArg().defaultsTo("legacy", new String[0]);
        NonOptionArgumentSpec var20 = var1.nonOptions();
        OptionSet var21 = var1.parse(p_main_0_);
        List var22 = var21.valuesOf(var20);

        if (!var22.isEmpty()) {
            System.out.println("Completely ignored arguments: " + var22);
        }

        String var23 = (String) var21.valueOf(var7);
        Proxy var24 = Proxy.NO_PROXY;

        if (var23 != null) {
            try {
                var24 = new Proxy(Type.SOCKS, new InetSocketAddress(var23, ((Integer) var21.valueOf(var8)).intValue()));
            } catch (Exception var43) {
                ;
            }
        }

        final String var25 = (String) var21.valueOf(var9);
        final String var26 = (String) var21.valueOf(var10);

        if (!var24.equals(Proxy.NO_PROXY) && func_110121_a(var25) && func_110121_a(var26)) {
            Authenticator.setDefault(new Authenticator() {
                private static final String __OBFID = "CL_00000828";

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(var25, var26.toCharArray());
                }
            });
        }

        int var27 = ((Integer) var21.valueOf(var15)).intValue();
        int var28 = ((Integer) var21.valueOf(var16)).intValue();
        boolean var29 = var21.has("fullscreen");
        boolean var30 = var21.has("checkGlErrors");
        boolean var31 = var21.has("demo");
        String var32 = (String) var21.valueOf(var14);
        PropertyMap var33 = (PropertyMap) (new GsonBuilder()).registerTypeAdapter(PropertyMap.class, new Serializer()).create().fromJson((String) var21.valueOf(var17), PropertyMap.class);
        File var34 = (File) var21.valueOf(var4);
        File var35 = var21.has(var5) ? (File) var21.valueOf(var5) : new File(var34, "assets/");
        File var36 = var21.has(var6) ? (File) var21.valueOf(var6) : new File(var34, "resourcepacks/");
        String var37 = var21.has(var12) ? (String) var12.value(var21) : (String) var11.value(var21);
        String var38 = var21.has(var18) ? (String) var18.value(var21) : null;
        String var39 = (String) var21.valueOf(var2);
        Integer var40 = (Integer) var21.valueOf(var3);
        Session var41 = new Session((String) var11.value(var21), var37, (String) var13.value(var21), (String) var19.value(var21));
        GameConfiguration var42 = new GameConfiguration(new GameConfiguration.UserInformation(var41, var33, var24), new GameConfiguration.DisplayInformation(var27, var28, var29, var30), new GameConfiguration.FolderInformation(var34, var36, var35, var38), new GameConfiguration.GameInformation(var31, var32), new GameConfiguration.ServerInformation(var39, var40.intValue()));
        Runtime.getRuntime().addShutdownHook(new Thread("Client Shutdown Thread") {
            private static final String __OBFID = "CL_00000829";

            public void run() {
                Minecraft.stopIntegratedServer();
            }
        });
        Thread.currentThread().setName("Client thread");
        (new MCHook(var42)).run();
    }

    private static boolean func_110121_a(String p_110121_0_) {
        return p_110121_0_ != null && !p_110121_0_.isEmpty();
    }
}
