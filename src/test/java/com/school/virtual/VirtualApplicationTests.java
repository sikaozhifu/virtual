package com.school.virtual;

import com.school.virtual.entity.User;
import com.school.virtual.mapper.NoticeMapper;
import com.school.virtual.mapper.UserMapper;
import com.school.virtual.utils.RandomValue;
import com.school.virtual.utils.UploadUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VirtualApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NoticeMapper noticeMapper;

    @Test
    void select() {
        List<User> userList = userMapper.selectList(null);
        for(User user:userList){
            user.setPassword("202cb962ac59075b964b07152d234b70");
            userMapper.updateById(user);
            System.out.println(user);
        }
    }

    String str = "纳兰妍若、南宫清笔、欧阳韵雪、北堂曝雪、慕容若嫣、司徒雨、欧阳缇娜、汝嫣浏茵、安陵忻美、司徒言韵、纳兰静怡、南宫语茜、冥光画影、慕云绾兮、浅执风羽、青桔笙歌、花墨梨笙、清歌馆颜、千顾无笙、古槿溪涵、古槿沾筱、慕容颜夕、百槿陌娴、何宫清姻、倾笛若幽、百里萦笙、年姬漪、君洛弦、冷慕言、倾冷月、傅昕臣、付卿泡、莫轻舞、安流烟、洛笔睛、林七许";
    String str2 = "南宫浅、白鹤染、上官秋雨、上官恋虹、上官灵罗、上官语凤、上官盼月、上官如冰、上官紫翎、上官蓝茜、上官玲儿、上官傲蝶、上官芸涌、上官玲珑、上官婉儿、上官曦芸、南宫采莲、司马心怡、独孤月莹、欧阳瑞雪\n"+
            "冷悠郁婷、冷悠静琳、冷悠晗萱、冷悠芷涵、冷悠依岚、冷悠若秋、冷悠诗怡、冷悠浅忆、冷悠魅予、冷悠若熙、冷悠晨琳、冷悠雨琳、冷悠绿萍、冷悠梦洁、冷悠书洁、冷悠玥怡、冷悠紫易、冷悠绮兰、冷悠慕凝、冷悠秋蝶、南宫若舞、纳容雨幽、欧阳纸鸢、杨霖暮雪、慕容获音、司马令姬、尉迟繁炽、长孙玲珑、柳芊依兰、上官弯翊";

    @Test
    void insert(){
//        String[] strList = str.split("、");
//        for(String s:strList){
//            User user = new User();
//            user.setName(s);
//            String phone = RandomValue.getTel();
//            user.setUsername(phone);
//            user.setPassword("123");
//            user.setPhone(phone);
//            user.setSex(RandomValue.getSex());
//            userMapper.insert(user);
//        }
        for (int i = 0; i < 1; i++) {
            User user = new User();
            user.setName(RandomValue.getChineseName());
            user.setSex(RandomValue.getSex());
            String phone = RandomValue.getTel();
            user.setUsername(phone);
            user.setPhone(phone);
            user.setPassword("123");
            int result = userMapper.insert(user);
            System.out.println(result);
        }
    }

    @Test
    void test_dir(){
        System.out.println(UploadUtils.getImgDirFile().getAbsolutePath());
    }

    @Test
    void del_notice(){
        int result = noticeMapper.deleteById(2);
        System.out.println("result" + result);
    }

}
