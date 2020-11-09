package controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Map;

@FacesValidator("decimalValidator")
public class DecimalValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

        Map<String, Object> attributes = uiComponent.getAttributes();
        double max = Double.parseDouble(attributes.get("max").toString());
        double min = Double.parseDouble(attributes.get("min").toString());


        FacesMessage msg = new FacesMessage(String.format("value must be in (%s; %s)", Double.toString(min), Double.toString(max)));
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        if(o == null || o.toString().equals("")) {
            throw new ValidatorException(msg);
        }
//        System.out.println(o.toString());
        double value = Double.parseDouble(o.toString());
        boolean isCorrect = (value > min && value < max);
        System.out.println(isCorrect);
        if (!isCorrect) {
            throw new ValidatorException(msg);
        }


    }
}