/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter_contornos;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.DirectMessage;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Miguel
 */
public class Metodos {
    private ConfigurationBuilder cb;
    private Twitter twitter;
    
    /**
     * Introducir claves para que se conecte a tu cuenta de Twitter y puedas utilizar 
     * este programa sin errores.
     * Los * representan donde tienes que introducir las claves.
     */
    public void clave(){
            cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("LvDvh1O59uDWrShwe7kBUXPfG")
                    .setOAuthConsumerSecret("r0xXqIZVjuSi11v0OAUCIJVjaunEUTfIsTw0HVAxgUyFyHDzoc")
                    .setOAuthAccessToken("847339034858762240-vEg6mva8GnITk37oiJlA008xKFUA0M5")
                    .setOAuthAccessTokenSecret("LnrfJbq0TkvGK2wMUVzDtb0oF4q49XhT78dLtS7IaQ9jF");
            TwitterFactory tf = new TwitterFactory(cb.build());
            twitter = tf.getInstance();
    }
    /**
     * Refresca tu cuenta de Twitter y te muestra lo nuevo que hay en tu perfil y lo nuevo
     * que pusieron tus seguidores.
     */
    public void verTimeLine(){
        List<String> statuses=null;
        String mensaje="Monstrando TimeLine \n";
        Status aux=null;
        try {
            statuses = twitter.getHomeTimeline();
            System.out.println("Refrescar ");
            for (Status status : statuses) {
                System.out.println(status.getUser().getName() + ":" +
                        status.getText());
            }   } catch (TwitterException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        Iterator<Status>miIterator=statuses.iterator();
            while(miIterator.hasNext()){
                aux=miIterator.next();
                mensaje+=aux.getUser().getName()+','+aux.getText()+"\n -- \n";
            }
    }
    /**
     * Poner un estado directo en tu perfil.
     * @param latestStatus 
     */
    public void tweetear(String latestStatus){
        List<Status> statuses=null;
        try {
            Status status = twitter.updateStatus(latestStatus);
            System.out.println("Actualizó correctamente el estado a [" + status.getText() + "].");
        } catch (TwitterException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Mandar un mensaje directo a alguien deseado a su cuenta de Twitter.
     */
    public void directMenssage(){
        try {
            DirectMessage message;
            message = twitter.sendDirectMessage("Mblanc01","Buenas, un saludo");
            System.out.println("Enviado: "+ message.getText() + " para @" + message.getRecipientScreenName());
        } catch (TwitterException ex) {
            Logger.getLogger(Twitter_Contornos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Realizas una busqueda por hastag o palabras y te muestra twits relacionados
     * con la palabra que escribiste.
     * @param busqueda 
     */
    public void buscarTrending(String busqueda){
        try {
            Query query = new Query(busqueda);
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
            }   } catch (TwitterException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}