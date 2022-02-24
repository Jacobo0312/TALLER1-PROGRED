package app;

import java.util.Scanner;

import comm.TCPConnection;
import event.*;

public class Application implements OnMessageListener {
    private Scanner scanner = new Scanner(System.in);

    private TCPConnection tcpConnection;

    public Application() {
        this.tcpConnection = TCPConnection.getInstance();
        tcpConnection.setPort(13552);
        tcpConnection.setIp("4.tcp.ngrok.io");
        tcpConnection.subscribe(this);
    }

    public void init() {
    tcpConnection.start();

        while (true) {
            String line = scanner.nextLine();

            if (line.equals("RTT")){
                rtt();
            }else if (line.equalsIgnoreCase("speed")){
                speed();
            }else{
                tcpConnection.sendMessage(line);
            }
            
        }

    }

    private void speed() {
        long initTime=System.currentTimeMillis();
        tcpConnection.sendMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum pretium lectus sed venenatis porta. Mauris sit amet congue justo, vel faucibus neque. Nullam dolor ligula, congue sollicitudin eros lobortis, imperdiet scelerisque urna. Nulla elit est, dapibus non nibh eget, accumsan tincidunt orci. Nunc tincidunt tempor ipsum, non dictum neque pharetra ac. Maecenas sodales eu ipsum nec blandit. Praesent varius consequat erat, in efficitur ipsum rutrum vitae. Maecenas porttitor, nunc non posuere maximus, ipsum magna sollicitudin nulla, in feugiat nulla eros id erat. Pellentesque non mauris urna. Nunc ullamcorper eleifend ipsum, et laoreet elit.Cras at auctor mi. Quisque egestas a neque vitae mollis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec non euismod lorem. Sed accumsan vel leo at imperdiet. Nulla facilisi. Aliquam non sodales quam, quis vestibulum enim. Nunc id arcu nisl. Vestibulum rutrum rhoncus velit, ut tristique ex malesuada id. Phasellus lacinia mi sagittis suscipit dictum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vestibulum blandit ex vel felis ornare, ut dapibus felis pulvinar. Phasellus gravida elit ligula, vitae consequat massa pretium eu.Vestibulum non nisi id magna tincidunt molestie. Vivamus laoreet augue leo, ut molestie tortor sollicitudin et. Morbi non mauris cursus, porttitor lorem sed, consectetur massa. Nulla at odio ac metus auctor tristique scelerisque a mauris. Aliquam et lacus urna. Nunc quam ligula, consequat et purus non, interdum elementum odio. Praesent nulla est, elementum consectetur imperdiet ac, egestas vitae nibh. Integer sapien odio, ultricies at tellus vel, suscipit rutrum diam. Interdum et malesuada fames ac ante ipsum primis in faucibus. Proin luctus congue vulputate. Praesent consequat feugiat quam at varius. Nullam varius tortor eget nunc molestie, id feugiat nulla varius. Vivamus porta finibus mi, id efficitur nibh tristique a. Aliquam viverra consequat tellus, at tristique orci vestibulum venenatis. Aenean porttitor suscipit est, quis egestas magna ornare non. Cras ultrices urna dui, eu aliquam velit porttitor sed.Nullam id turpis quis nibh ultricies porta. Vestibulum nec neque nec libero rhoncus tempor ac eu tortor. Praesent sed varius urna. Quisque sit amet dui convallis, gravida libero nec, laoreet dolor. Fusce congue felis vel nisl suscipit, eu ultrices lorem varius. Praesent placerat finibus efficitur. In nec ornare diam. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur vitae aliquam eros. Nunc mi turpis, sollicitudin in fermentum in, elementum quis enim. Sed quis leo massa. Maecenas turpis justo, feugiat sed dignissim sed, dictum ut leo. Nunc faucibus tempor elit, pulvinar tincidunt purus. Duis ut convallis nibh. In hac habitasse platea dictumst.Cras fringilla dui non eros varius, eu lacinia urna gravida. Quisque ut urna scelerisque, dapibus eros ut, aliquet lacus. Donec hendrerit sem eros, eget euismod odio sollicitudin eu. Mauris mattis hendrerit iaculis. Nam faucibus egestas enim id tincidunt. Nulla vel lacus dictum, viverra est vel, porta dui. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Phasellus maximus dolor quam, non convallis quam feugiat volutpat.Fusce et leo maximus, sodales massa in, euismod eros. Sed et tincidunt augue, in interdum velit. Cras sollicitudin libero sit amet sapien rutrum tempor. Aenean sagittis malesuada enim, sit amet mattis eros finibus vitae. Pellentesque risus purus, pharetra in tincidunt sit amet, vestibulum non libero. Suspendisse vel ante id sapien rhoncus dictum ac quis justo. Suspendisse et venenatis enim, vitae dignissim tortor. Donec tortor turpis, efficitur in est non, pharetra venenatis mauris. Nunc euismod, odio a ullamcorper venenatis, sem arcu congue nunc, ac accumsan augue quam ut massa. Phasellus mollis dictum sollicitudin. Suspendisse sapien ligula, interdum in velit eget, egestas convallis leo. Nulla pretium tristique volutpat.Curabitur sit amet semper mi. Proin vel efficitur metus. Fusce massa eros, iaculis sit amet diam eget, consequat fermentum ex. Sed non arcu et augue ornare faucibus nec non nulla. Aenean congue quam euismod, feugiat orci sit amet, mollis massa. Vestibulum porttitor mi non ipsum ultricies congue. Aliquam erat volutpat. Duis varius elit vel sagittis pharetra. Etiam dictum felis nec ultrices molestie. Donec ac ipsum ante. Phasellus volutpat ipsum arcu, sit amet accumsan mi varius ut. Nullam pharetra, velit facilisis imperdiet sagittis, eros magna bibendum lacus, dignissim rutrum nisi diam a enim. Ut interdum tempor massa, et lacinia dolor tempus elementum. Nunc fermentum aliquam risus sit amet dapibus. Maecenas erat sem, congue quis elit eget, molestie iaculis eros. Cras tempor risus id neque vestibulum venenatis. Pellentesque ut sapien pulvinar, malesuada nisl quis, lacinia orci. Suspendisse posuere malesuada mauris, id porttitor enim faucibus quis. Integer non sapien blandit, ornare leo at, facilisis nulla. Sed et consectetur lorem. Curabitur in mi porttitor, elementum velit vel, placerat risus. Suspendisse suscipit, felis sit amet pharetra dapibus, ipsum sapien bibendum eros, a finibus eros turpis vitae enim. Phasellus dictum lacus ut tincidunt lobortis. Proin bibendum nec nunc ut tincidunt.Nam vitae quam maximus, ultrices mi non, imperdiet ligula. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec quam ligula, bibendum quis viverra eget, scelerisque sed nisi. Sed bibendum enim dignissim fringilla vulputate. Cras faucibus quam nec fermentum elementum. Mauris sit amet lectus a metus viverra fringilla. Proin augue urna, posuere at luctus sed, tempor at justo. Donec varius dolor dui, vel pretium velit vulputate eget. Proin vestibulum augue sed pellentesque euismod. Phasellus justo lacus, sollicitudin sit amet neque vitae, commodo convallis sem. Fusce nec ullamcorper tortor, vel iaculis massa.Donec at leo ac odio vestibulum sagittis. Nam rutrum placerat metus, sed ornare tortor fermentum vitae. Aenean lorem massa, consectetur a fringilla ut, rutrum et justo. Ut vitae tellus vel magna mollis lacinia. Morbi et volutpat massa. In gravida ornare justo non lacinia. Quisque vitae elit a magna tincidunt lobortis et vel nibh. Donec eget dolor dolor. Suspendisse potenti. Morbi non mauris quis lorem convallis faucibus at eu tellus. Vestibulum pellentesque erat ut sapien sagittis, a lacinia ante vulputate. Maecenas semper mauris ex, eget ultricies arcu tempor in.Sed commodo erat id ipsum lacinia ullamcorper. Proin metus nibh, cursus vel turpis eu, aliquam interdum mauris. Nullam maximus nunc ut dolor placerat ultrices. Nunc lacus felis, dignissim in efficitur eget, auctor eget ex. Sed egestas, metus in luctus aliquet, neque felis imperdiet mi, posuere euismod velit neque id diam. Curabitur placerat mollis sem a venenatis. Donec consequat augue pharetra erat malesuada, quis tempus metus ultricies. Donec dignissim nec justo a mattis. Sed lacinia lorem nulla, ultricies dapibus mi dapibus a. Cras bibendum laoreet sapien, eget porta diam sollicitudin ut.Maecenas quis neque vitae diam fringilla sodales in a augue. Duis finibus eros at rutrum venenatis. Praesent eros ligula, tincidunt vitae elementum eget, ultrices id tellus. Praesent tincidunt metus arcu, id sodales tellus aliquam ut. Vestibulum vestibulum egestas sem, eget consequat massa finibus vel. Sed viverra ligula a lorem condimentum, et varius justo consectetur. Quisque risus augue, pretium a condimentum nec, elementum vel lorem. Phasellus purus urna, finibus in congue non, dictum sit amet nisl. Nunc lacinia eros risus, vitae porta neque efficitur eu. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Quisque rutrum aliquet cursus. Duis eu quam metus.Etiam vitae suscipit justo. Duis volutpat, sapien eu suscipit scelerisque, nisl diam cursus mauris, viverra pulvinar est ipsum non felis. Nulla ultrices eros eget metus elementum faucibus. Suspendisse sit amet urna sed mi feugiat gravida nec ut elit. Aenean at egestas eros, sit amet orci.");


        long finalTime=tcpConnection.getTime();

        long secods=(initTime-finalTime)/1000;

        double kbs=8.192/secods;

        System.out.println(kbs+" KB/S");

    }

    private void rtt() {
        long initTime=System.currentTimeMillis();
        tcpConnection.sendMessage("RTT:Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam mollis diam eu tortor condimentum, vitae porta magna vestibulum. Cras tincidunt eros nunc. Sed vehicula euismod enim nec auctor. Donec venenatis tincidunt ipsum, sit amet imperdiet quam viverra non. Donec ex sem, facilisis eget vehicula vel, ullamcorper vestibulum elit. Integer imperdiet finibus leo. Suspendisse consequat tellus vel ipsum imperdiet rhoncus. Proin eleifend condimentum aliquam. Nullam porta libero nec aliquet efficitur. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc a ornare dui, eu tempor nisl.Quisque aliquet, tortor et finibus iaculis, ex eros porta ligula, et dictum lectus elit non nisl. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus a ullamcorper massa. Morbi efficitur dui a gravida convallis. Vestibulum eu accumsan massa. Integer eu ipsum eget mi euismod vehicula eu nec justo. Vestibulum gravida elit aenean.");
        


        long finalTime=tcpConnection.getTime();

        System.out.println(((initTime-finalTime)/1000)+" SECONDS");

    }

    @Override
    public void showMessage(String line) {
        System.out.println(line);
        
    }

}
