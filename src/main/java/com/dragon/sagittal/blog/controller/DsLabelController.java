package com.dragon.sagittal.blog.controller;


import com.dragon.sagittal.blog.common.R;
import com.dragon.sagittal.blog.common.exceptionhandler.GuliException;
import com.dragon.sagittal.blog.common.httpstatus.MyHttpStatus;
import com.dragon.sagittal.blog.entity.DsLabel;
import com.dragon.sagittal.blog.service.DsLabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ChunYu Sagittal
 * @since 2021-08-25
 */
@RestController
@RequestMapping("/serviceuser/ds-label")
@CrossOrigin
@Api(tags = "Label API接口")
public class DsLabelController {

    @Resource
    private DsLabelService dsLabelService;

    @ApiOperation(value = "添加标签")
    @PostMapping("/add")
    public R addLable(@Valid @RequestBody DsLabel dsLabel) {
        boolean b = dsLabel.checkData();
        if (!b) {
            throw new GuliException(MyHttpStatus.LABEL_DATA_IS_NOT_COMPLETE);
        }
        boolean save = dsLabelService.save(dsLabel);
        if (save) {
            return R.ok();
        }
        throw new GuliException(MyHttpStatus.ADD_LABEL_FAIL);
    }

    @ApiOperation(value = "根据ID删除标签")
    @DeleteMapping("/delete/{labelId}")
    public R deleteLable(
            @ApiParam(name = "labelId", value = "标签ID", required = true)
            @PathVariable Long labelId
    ) {
        boolean b = dsLabelService.removeById(labelId);
        if (b) {
            return R.ok();
        }
        throw new GuliException(MyHttpStatus.DELETE_LABEL_FAIL);
    }

    @ApiOperation(value = "根据ID修改标签")
    @PutMapping("/update/lable")
    public R updateLabe(@Valid @RequestBody DsLabel dsLabel) {
        boolean b = dsLabel.checkDataAll();
        if (!b) {
            throw new GuliException(MyHttpStatus.LABEL_DATA_IS_NOT_COMPLETE);
        }
        b = dsLabelService.updateById(dsLabel);
        if (b) {
            return R.ok();
        }
        throw new GuliException(MyHttpStatus.UPDATE_LABEL_FAIL);
    }

    @ApiOperation(value = "查找所有标签")
    @GetMapping("/select/all")
    public R selectAllLable() {
        Map<String, Object> map = dsLabelService.getMap(null);
        return R.ok().data("item", map);
    }

}

