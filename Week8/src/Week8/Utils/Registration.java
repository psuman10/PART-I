package Week8.Utils;

import Week8.Constants;
import Week8.FileHandler;

public class Registration {

    public String registerUser(String[] userDetail) {
        FileHandler fh = new FileHandler();
        return fh.CreateOrAdd(Constants.userDetailFileName, userDetail);
    }
}
