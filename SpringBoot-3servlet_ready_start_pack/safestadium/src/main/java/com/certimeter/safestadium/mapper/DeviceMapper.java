package com.certimeter.safestadium.mapper;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certimeter.safestadium.pojo.Device;

@Mapper
public interface DeviceMapper {

	@Select("SELECT "
			+ "username AS username,"
			+ "id_device AS idDevice,"
			+ "firebase_registration_token AS firebaseRegistrationToken "
			+ "FROM device "
			+ "WHERE username=#{username} AND id_device=#{idDevice} ")
	Device getDeviceByUsernameAndIdDevice(@Param("username") String username, @Param("idDevice") String idDevice);

	@Select("SELECT "
			+ "username AS username,"
			+ "id_device AS idDevice,"
			+ "firebase_registration_token AS firebaseRegistrationToken "
			+ "FROM device ")
	ArrayList<Device> getAllDevice();
	
	@Select("SELECT "
			+ "username AS username,"
			+ "id_device AS idDevice,"
			+ "firebase_registration_token AS firebaseRegistrationToken "
			+ "FROM device "
			+ "WHERE username=#{username}")
	ArrayList<Device> getDeviceByUsername(@Param("username") String username);
	
	@Select("SELECT "
			+ "username AS username,"
			+ "id_device AS idDevice,"
			+ "firebase_registration_token AS firebaseRegistrationToken "
			+ "FROM device "
			+ "WHERE id_device=#{idDevice} ")
	ArrayList<Device> getDeviceByIdDevice(@Param("idDevice") String idDevice);

	@Select("SELECT "
			+ "username AS username,"
			+ "id_device AS idDevice,"
			+ "firebase_registration_token AS firebaseRegistrationToken "
			+ "FROM device "
			+ "WHERE firebase_registration_token=#{firebaseRegistrationToken}")
	ArrayList<Device> getDeviceByFirebaseRegistrationToken(@Param("firebaseRegistrationToken") String firebaseRegistrationToken);
	
	@Insert("INSERT INTO device "
			+ "(username, id_device, operating_system, login_timestamp) "
			+ " VALUES "
			+ "(#{username}, #{idDevice}, #{operatingSystem}, #{loginTimestamp})")
	int insertDevice(@Param("username") String username, @Param("idDevice") String idDevice, @Param("operatingSystem") String operatingSystem, @Param("loginTimestamp") Timestamp loginTimestamp);
	
	@Update("UPDATE device "
			+ "SET firebase_registration_token = #{firebaseRegistrationToken} "
			+ "WHERE username = #{username} "
			+ "AND id_device = #{idDevice} ")
	int updateFirebaseRegistrationTokenByUsernameAndIdDevice(@Param("username") String username, @Param("idDevice") String idDevice, @Param("firebaseRegistrationToken") String firebaseRegistrationToken);

	@Delete("DELETE FROM device WHERE id_device = #{idDevice} ")
	int deleteDeviceByIdDevice(@Param("idDevice") String idDevice);
	
	@Delete("DELETE FROM device WHERE username=#{username} AND id_device=#{idDevice} ")
	int deleteDeviceByUsernameAndIdDevice(@Param("username") String username, @Param("idDevice") String idDevice);
	
}
