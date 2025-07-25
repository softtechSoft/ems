package com.softtech.actionForm;

import java.util.ArrayList;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.softtech.com.DepartmentInfo;
import com.softtech.com.EptypeInfo;
//import com.softtech.entity.EmployeepType;

/**
 * 概要：給料詳細画面用Bean
 *
 * 作成者：劉@ソフトテク
 * 作成日：2021/7/20
 */
public class EmployeeEditFormBean {

	//ユーザID
	private String employeeID;

	//ユーザ名
	@NotEmpty(message = "氏名を入力してください。")
	@Size(max = 12, message = "氏名は12文字以内で入力してください。")
	private String employeeName;

	// 住所
	@NotEmpty(message = "住所を入力してください。")
//	@Pattern(regexp = "^[^ -~｡-ﾟ]*$", message = "住所を全角で入力してください。")
	@Size(max = 200, message = "住所は200文字以内で入力してください。")
	private String address;

	// 性別
	private String sex;

	//生年月日
	@NotEmpty(message = "生年月日を入力してください。")
	private String birthday;

	//年齢
	private String age;

	//入社年月日
	@NotEmpty(message = "入社年月日を入力してください。")
	private String joinedDate;





	//社齢
	public String joinedTime;

	public String joinedAge;

	//郵便番号
	@NotEmpty(message = "郵便番号を入力してください。")
	// @Size (min=7 , max=7, message = "郵便番号は　半角７桁数字で入力して下さい。")
//	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号「xxx-xxxx」の形式で入力してください。")
	public String postCode;

	//電話番号
	@NotEmpty (message = "電話番号を入力してください")
	//@Size (max = 15 , message = "電話番号は　半角１５桁数字以内で入力して下さい。")
//	@Pattern(regexp = "^[0-9]{2,4}-[0-9]{3,4}-[0-9]{4}$", message = "電話番号は「xxx-xxxx-xxxx」の形式で入力してください。")
	public String phoneNumber;

	//入更新日
	public String updateDate;

	// タイプ
	public String epType;

	public String getEpType() {
		return epType;
	}

	public void setEpType(String epType) {
		this.epType = epType;
	}
	//個人番号
	@NotEmpty(message = "個人番号を入力してください。")
	@Size(min = 12 , max = 12, message = "個人番号は半角12桁数字で入力してください。")
	@Pattern(regexp = "^[0-9]*$", message = "個人番号は半角数字で入力してください。")
	private String personNumber;

	//所属部門
	private String department;
	//タイプ
	public ArrayList<EptypeInfo> epTypeInfoList;
	//選択されたid
	private Integer selectedepTypeId;
	//タイプ
	public ArrayList<DepartmentInfo> depTypeInfoList;
	//選択されたid
	private Integer selectedDepTypeId;

	public ArrayList<DepartmentInfo> getDepTypeInfoList() {
		return depTypeInfoList;
	}

	public void setDepTypeInfoList(ArrayList<DepartmentInfo> depTypeInfoList) {
		this.depTypeInfoList = depTypeInfoList;
	}

	public ArrayList<EptypeInfo> getEpTypeInfoList() {
		return epTypeInfoList;
	}

	public void setEpTypeInfoList(ArrayList<EptypeInfo> epTypeInfoList) {
		this.epTypeInfoList = epTypeInfoList;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getJoinedTime() {
		return joinedTime;
	}

	public void setJoinedTime(String joinedTime) {
		this.joinedTime = joinedTime;
	}

	public String getJoinedAge() {
		return joinedAge;
	}

	public void setJoinedAge(String joinedAge) {
		this.joinedAge = joinedAge;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFormattedPhoneNumber() {
//	    if (phoneNumber != null && phoneNumber.length() >= 10) {
//	        if (phoneNumber.length() == 10) {
//	            return phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6);
//	        } else if (phoneNumber.length() == 11) {
//	            return phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 7) + "-" + phoneNumber.substring(7);
//	        }
//	    }
	    return phoneNumber; // 防止空值报错
	}

	public String getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}



	public String getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getSelectedepTypeId() {
		return selectedepTypeId;
	}

	public void setSelectedepTypeId(Integer selectedepTypeId) {
		this.selectedepTypeId = selectedepTypeId;
	}

	public Integer getSelectedDepTypeId() {
		return selectedDepTypeId;
	}

	public void setSelectedDepTypeId(Integer selectedDepTypeId) {
		this.selectedDepTypeId = selectedDepTypeId;
	}

	public class EmployeeInfo {
		public Integer id;
		public String name;

		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
//		private String password;
//
//	    // Getter for 'password'
//	    public String getPassword() {
//	        return password;
//	    }
//
//	    // Setter for 'password' (nếu cần)
//	    public void setPassword(String password) {
//	        this.password = password;
//	    }


	}


}

