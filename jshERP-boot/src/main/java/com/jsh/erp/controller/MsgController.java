package com.jsh.erp.controller;

import com.jsh.erp.datasource.entities.Msg;
import com.jsh.erp.service.msg.MsgService;
import com.jsh.erp.utils.BaseResponseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ji sheng hua jshERP
 */
@RestController
@RequestMapping(value = "/msg")
@Api(tags = {"消息管理"})
public class MsgController {
    private Logger logger = LoggerFactory.getLogger(MsgController.class);

    @Resource
    private MsgService msgService;

    /**
     * 根据状态查询消息
     * @param status
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/getMsgByStatus")
    @ApiOperation(value = "根据状态查询消息")
    public BaseResponseInfo getMsgByStatus(@RequestParam("status") String status,
                                           HttpServletRequest request)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        try {
            List<Msg> list = msgService.getMsgByStatus(status);
            res.code = 200;
            res.data = list;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }

    /**
     * 批量更新状态
     * @param ids
     * @param status
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/batchUpdateStatus")
    @ApiOperation(value = "批量更新状态")
    public BaseResponseInfo batchUpdateStatus(@RequestParam("ids") String ids,
                                              @RequestParam("status") String status,
                                              HttpServletRequest request)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        try {
            msgService.batchUpdateStatus(ids, status);
            res.code = 200;
            res.data = "更新成功";
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }

    /**
     * 根据状态查询数量
     * @param status
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/getMsgCountByStatus")
    @ApiOperation(value = "根据状态查询数量")
    public BaseResponseInfo getMsgCountByStatus(@RequestParam("status") String status,
                                                HttpServletRequest request)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        try {
            Map<String, Long> map = new HashMap<String, Long>();
            Long count = msgService.getMsgCountByStatus(status);
            map.put("count", count);
            res.code = 200;
            res.data = map;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }
}
