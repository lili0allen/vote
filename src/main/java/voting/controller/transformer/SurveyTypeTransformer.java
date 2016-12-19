package voting.controller.transformer;

import voting.domain.SurveyType;

import java.util.ArrayList;
import java.util.List;

import static voting.domain.SurveyType.INVITATION;
import static voting.domain.SurveyType.STANDALONE;

public class SurveyTypeTransformer {

    public SurveyType surveyTypeFromString(String surveyTypeString) {
        if("Standalone".equals(surveyTypeString)) {
            return STANDALONE;
        }
        if("Invitation".equals(surveyTypeString)) {
            return SurveyType.INVITATION;
        }

        throw new IllegalArgumentException("surveyTypeInternalValue not supported: " + surveyTypeString);

    }

    public String surveyTypeToString(SurveyType surveyType) {
        if(STANDALONE == surveyType) {
            return "Standalone";
        }

        if(INVITATION == surveyType) {
            return "Invitation";
        }

        throw new IllegalArgumentException("surveyType not supported: " + surveyType);
    }

    public List<String> surveyTypeString(){
        List<String> types = new ArrayList<String>();
        for(SurveyType type : SurveyType.values()){
            types.add(this.surveyTypeToString(type));
        }
        return types;
    }
}
