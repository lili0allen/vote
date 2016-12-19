package voting.controller.transformer;

import voting.domain.VoteOption;
import java.util.ArrayList;
import java.util.List;

public class VoteOptionTransformer {
    public VoteOption voteOptionFromString(String voteOptionString) {
        if("Good".equals(voteOptionString)) {
            return VoteOption.GOOD;
        }
        if("Fair".equals(voteOptionString)) {
            return VoteOption.FAIR;
        }
        if("Poor".equals(voteOptionString)) {
            return VoteOption.POOR;
        }

        throw new IllegalArgumentException("VoteOptionString not supported: " + voteOptionString);

    }

    public String voteOptionToString(VoteOption voteOption) {
        if(VoteOption.GOOD == voteOption) {
            return "Good";
        }
        if(VoteOption.FAIR == voteOption) {
            return "Fair";
        }
        if(VoteOption.POOR == voteOption) {
            return "Poor";
        }

        throw new IllegalArgumentException("VoteOption not supported: " + voteOption);
    }

    public List<String> VoteOptionString(){
        List<String> options = new ArrayList<String>();
        for(VoteOption option : VoteOption.values()){
            options.add(this.voteOptionToString(option));
        }
        return options;
    }
}
