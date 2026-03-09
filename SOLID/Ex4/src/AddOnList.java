import java.util.ArrayList;
import java.util.List;

public class AddOnList {
    public  List<AddOnRule> list = new ArrayList<>();

    public AddOnList(){
        list.add(new MessAddOn());
        list.add(new LaundryAddOn());
        list.add(new GymAddOn());
    }
}