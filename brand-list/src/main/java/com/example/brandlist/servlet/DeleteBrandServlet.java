package com.example.brandlist.servlet;

import com.alibaba.fastjson.JSON;
import com.example.brandlist.entity.Brand;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.brandlist.entity.Brand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/deleteBrand")
public class DeleteBrandServlet extends HttpServlet {

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");

        // 获取品牌 ID
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Brand ID is required\"}");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);

            // 从 ServletContext 获取品牌列表
            ServletContext servletContext = req.getServletContext();
            List<Brand> brandList = (List<Brand>) servletContext.getAttribute("brands");

            // 如果品牌列表为空，则使用默认的品牌列表
            if (brandList == null) {
                brandList = new ArrayList<>(getDefaultBrandList());
            }

            // 找到并删除指定 ID 的品牌
            boolean removed = brandList.removeIf(brand -> brand.getId() == id);

            // 更新 ServletContext 中的品牌列表
            servletContext.setAttribute("brands", brandList);

            // 返回相应结果
            if (removed) {
                resp.getWriter().write("{\"message\": \"Brand deleted successfully\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\": \"Brand not found\"}");
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Invalid Brand ID format\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Failed to delete brand: " + e.getMessage() + "\"}");
            e.printStackTrace(); // 记录异常信息
        }
    }

    // 提供默认的品牌列表（与 BrandServlet 中的实现类似）
    private List<Brand> getDefaultBrandList() {
        return List.of(
                Brand.builder().id(101).companyName("apple").brandName("iPhone16").description("苹果-iPhone16").ordered(1).build(),
                Brand.builder().id(102).companyName("huawei").brandName("mate60").description("华为-mate60").ordered(2).build(),
                Brand.builder().id(103).companyName("benz").brandName("mercedes").description("奔驰-梅赛德斯").ordered(3).build());
    }
}