package com.example.customersdatabase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    Top10Repository top10Repository;

    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/showcompanylist")
    public String showCompanyList(Model model)
    {
        model.addAttribute("companies", companyRepository.findAll());
        return "companylist";
    }

    @RequestMapping("/showcustomerlist")
    public String showCustomerList(Model model)
    {
        model.addAttribute("customers", customerRepository.findAll());
        return "list";
    }

    @GetMapping("/addcustomer")
    public String newCustomer(Model model)
    {
        model.addAttribute("companies", companyRepository.findAll());
        model.addAttribute("customer", new Customer());
        return "customerform";
    }

    @PostMapping("/addcustomer")
    public String processCustomer(@Valid @ModelAttribute Customer customer, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "customerform";
        }
        customerRepository.save(customer);
        return "redirect:/showcustomerlist";
    }


    @RequestMapping("/detailCustomer/{id}")
    public String showDetail(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("customer", customerRepository.findById(id).get());
        return "showCustomer";
    }


    @RequestMapping("/updateCustomer/{id}")
    public String updateCar(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("companies", companyRepository.findAll());
        model.addAttribute("customer", customerRepository.findById(id));
        return "customerform";
    }

    @RequestMapping("/deleteCustomer/{id}")
    public String deleteCar(@PathVariable("id") long id)
    {
        customerRepository.deleteById(id);
        return "redirect:/showcustomerlist";
    }


    ////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/addcompany")
    public String newCompany(Model model)
    {
        model.addAttribute("company", new Company());
        return "companyform";
    }

    @PostMapping("/addcompany")
    public String processCompany(@Valid @ModelAttribute Company company, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "companyform";
        }
        companyRepository.save(company);
        return "redirect:/showcompanylist";
    }


    @RequestMapping("/detailCompany/{id}")
    public String showDetailCompany(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("company", companyRepository.findById(id).get());
        return "showCompany";
    }


    @RequestMapping("/updateCompany/{id}")
    public String updateCompany(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("company", companyRepository.findById(id));
        return "companyform";
    }

    @RequestMapping("/deleteCompany/{id}")
    public String deleteComapny(@PathVariable("id") long id)
    {
        companyRepository.deleteById(id);
        return "redirect:/showcompanylist";
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/search")
    public String getSearch()
    {
        return "searchform";
    }

    @PostMapping("/search")
    public String showSearchResults(HttpServletRequest request, Model model)
    {
        String searchString = request.getParameter("search");
        model.addAttribute("search",searchString);
        model.addAttribute("customers",customerRepository.findAllByLastnameContainingIgnoreCase(searchString));
        return "list";
    }

    @PostMapping("/searchbycity")
    public String showSearchByCityResults(HttpServletRequest request, Model model)
    {
        String searchString = request.getParameter("search");
        model.addAttribute("search",searchString);
        model.addAttribute("customers", customerRepository.findAllByCityContainingIgnoreCase(searchString));
        return "list";
    }

    @RequestMapping("/top10companies")
    public String showTop10(Model model)
    {
        model.addAttribute("companies", top10Repository.findAll());
        return "companylist";
    }


}
