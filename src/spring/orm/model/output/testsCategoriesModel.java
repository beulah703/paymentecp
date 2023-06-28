package spring.orm.model.output;

public class testsCategoriesModel {
	@Override
	public String toString() {
		return "testsCategoriesModel [test_category=" + test_category + "]";
	}

	String test_category;

	public String getTest_category() {
		return test_category;
	}

	public void setTest_category(String test_category) {
		this.test_category = test_category;
	}

	public testsCategoriesModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public testsCategoriesModel(String test_category) {
		super();
		this.test_category = test_category;
	}

}
