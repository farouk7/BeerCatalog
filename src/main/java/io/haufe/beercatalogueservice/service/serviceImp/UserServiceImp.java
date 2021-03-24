package io.haufe.beercatalogueservice.service.serviceImp;

import java.util.Collection;
import java.util.Optional;

import io.haufe.beercatalogueservice.models.Users;
import io.haufe.beercatalogueservice.repository.UserRepository;
import io.haufe.beercatalogueservice.service.IService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImp implements IService<Users> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Users saveOrUpdate(Users user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Users findbyString(String user) {
        return userRepository.findByEmail(user);
    }
    @Override
    public String deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            userRepository.deleteById(id);
            jsonObject.put("message", "User deleted successfully");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}