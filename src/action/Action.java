package action;

import java.io.Serializable;

public class Action implements Serializable {

	private ActionType actionType;
	private Object[] properties;
	
	public Action(ActionType actionType, Object[] properties) {
		this.actionType = actionType;
		this.properties = properties;
	}
	
	public ActionType getActionType() {
		return actionType;
	}
	
	public Object getPropertie(int i) {
		return properties[i];
	}
}
