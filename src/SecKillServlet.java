import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 秒杀活动servlet
 * @author psy07
 *
 */
public class SecKillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SecKillServlet() {
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //生成用户id
		String userid = new Random().nextInt(50000) + "";
		//获取首页传入的商品id
		String prodid = request.getParameter("prodid");
		
		//通过秒杀的放法判断用户是否秒杀成功
		boolean if_success = SecKill_redis.doSecKill(userid, prodid);
		
		//调用Lua脚本秒杀
		//boolean if_success=SecKill_redisByScript.doSecKill(userid,prodid);
		
		response.getWriter().print(true);
	}

}
