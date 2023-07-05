package dev.mybatise;

import dev.mybatise.sql.VipUserExMapper;
import io.github.mybatise.codegen.UserSupport;
import dev.mybatise.model.Address;
import dev.mybatise.model.User;
import dev.mybatise.model.VipUser;
import dev.mybatise.sql.UserExMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wes Lin
 */
public class MapperTest {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqliteSqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        try (SqlSession session = sqliteSqlSessionFactory.openSession()) {
            UserExMapper mapper = session.getMapper(UserExMapper.class);
            List<User> users = mapper.selectAll(UserSupport.mather());
            System.out.println(users.size());
            User record = new User();
            record.setUserName("userName");
            record.setCreatedWhen(LocalDateTime.now());
            Address address = new Address();
            address.setProvince("福建省");
            address.setCity("厦门市");
            address.setCounty("思明区");
            record.setAddress(address);
            mapper.insert(record);
//            session.commit();
            System.out.println(record.getUserId());
            record.setUserName("222");
            address.setCounty("湖里区");
            mapper.updateByPrimary(record);

            UserSupport.Mather mather = UserSupport.mather()
                    .userIdEqualTo(record.getUserId())
                    .addressEqualTo(address)
                    .genderIsNull()
                    .passwordIsBetween("1", "2")
                    .userNameIsNotBetween("zan", "aaa")
                    .userIdIsIn(Arrays.asList("1", "2"));
            List<User> userList = mapper.selectManyWithSupport(UserSupport.of()
                    .all()
                    .withMather(mather));
            System.out.println(userList.size());

            User record1 = mapper.selectByPrimary("1");
            System.out.println(record1.getUserName());

            mapper.deleteByPrimary("1");

            VipUserExMapper vipUserExMapper = session.getMapper(VipUserExMapper.class);
            VipUser vipUser = new VipUser();
            vipUser.setUserName("test1234");
            vipUserExMapper.insert(vipUser);
            System.out.println(vipUser.getUserId());
        }
    }
}
