package com.example.vko10;

import java.util.ArrayList;

public class History {
    private ArrayList<String> url_history = new ArrayList();
    private int index = 0;



    public History(String url){
        url_history.add(url);


    }

    public void addUrl(String url) {
        boolean found = false;

        //checks if the new url is already in the arraylist
        for (String i : url_history) {
            if (url == i) {
                found = true;
            }
        }


        //if it is not
        if (!found) {//if it is



            //if arraylist is longer than 10
            if(url_history.size()>9){
                url_history.remove(0);
                index--;
            }



            if (index < url_history.size()-1) { //erases newer urls
                int amount = url_history.size();
                for (int i = index + 1; i < amount; i++) {

                    url_history.remove(index+1);
                }

            }


            url_history.add(url);
            index++;

        }
    }


    public String previous(){
        if(index > 0) {
            index--;
            return url_history.get(index);
        } else {
            return url_history.get(index);
        }
    }

    public String next(){
        if(index < url_history.size()-1) {
            index++;
            return url_history.get(index);
        } else {
            return url_history.get(index);
        }
    }


    public int getIndex(){
        return index;
    }

    public int getAmount(){
        return url_history.size();
    }
}
