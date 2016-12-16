package voting.controller.transformer;

import voting.domain.SurveyType;
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
}
