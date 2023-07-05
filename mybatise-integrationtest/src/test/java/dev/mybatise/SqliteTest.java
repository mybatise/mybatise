package dev.mybatise;

import dev.mybatise.model.Address;
import dev.mybatise.model.User;
import dev.mybatise.sql.UserExMapper;
import io.github.mybatise.codegen.UserSupport;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Wes Lin
 */
public class SqliteTest {

    private static Logger LOGGER = LoggerFactory.getLogger(SqliteTest.class);
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);
    }

    private <T> void acceptMapper(Class<T> mapperClass, Consumer<T> consumer) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            T mapper = session.getMapper(mapperClass);
            consumer.accept(mapper);
        }
    }

    @Test
    public void selectTest(){
        acceptMapper(UserExMapper.class, mapper -> {
            UserSupport.Mather mather = UserSupport.mather();
            mather.userNameEqualTo("mick");
            List<User> users = mapper.selectAll(mather);
            Assert.assertNotNull(users);
            Assert.assertNotNull(users.get(0).getPassword());

            UserSupport support = UserSupport.of()
                    .userId()
                    .userName()
                    .address()
                    .withMather(mather);
            User user = mapper.selectOneWithSupport(support);
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getUserId());
            Assert.assertNull(user.getPassword());

            User user1 = mapper.selectByPrimary(user.getUserId());
            Assert.assertNotNull(user1);
        });
    }

    @Test
    public void insertTest() {
        acceptMapper(UserExMapper.class, mapper -> {
            User record = new User();
            record.setUserName("userName");
            record.setCreatedWhen(LocalDateTime.now());
            Address address = new Address();
            address.setProvince("福建省");
            address.setCity("厦门市");
            address.setCounty("思明区");
            record.setAddress(address);
            mapper.insert(record);
            Assert.assertNotNull(record.getUserId());

            record.setUserId(null);
            UserSupport support = UserSupport.of()
                    .createdBy()
                    .userName()
                    .withValue(record);
            mapper.insertWithSupport(support);
            User user = mapper.selectByPrimary(record.getUserId());
            Assert.assertNotNull(user.getUserId());
            Assert.assertNull(user.getAddress());
        });
    }

}
