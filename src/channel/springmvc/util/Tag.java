package channel.springmvc.util;

import java.util.Date;

/**
 * Created by hadoop on 17-4-8.
 */
public class Tag implements Comparable<Tag>{
    public String name;
    private int count;
    private long lastDate;

    public Tag(String n){
        name = n;
        count = 1;
        lastDate = new Date().getTime();
    }

    public Tag(String n, int c){
        name = n;
        count = c;
        lastDate = new Date().getTime();
    }

    public Tag increaseCount(){
        count++;
        return this;
    }
    
    public int getCount(){return count;}

    public String getName(){return name;}
    
    @Override
    public String toString() {
        return name + ":" + String.valueOf(count);
    }

    @Override
    public int compareTo(Tag tag) {
        if(count > tag.count ){
            return -1;
        } else if (count < tag.count){
            return 1;
        } else {
            if (lastDate < tag.lastDate) {
                 return 1;
            } else {
                return -1;
            }
        }
    }
}
