package edu.poly.fpt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.poly.fpt.entities.PasswordResetToken;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.entities.TaiKhoan;
import edu.poly.fpt.repositories.PasswordResetTokenRepository;
import edu.poly.fpt.repositories.TaikhoanRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TaikhoanServiceImpl implements TaikhoanService,UserDetailsService{
@Autowired
private TaikhoanRepository taikhoanRepository;

@Autowired
private PasswordResetTokenRepository passwordResetTokenRepository;


@Override
public PasswordResetToken getPasswordResetToken(final String token) {
	return passwordResetTokenRepository.findByToken(token);
}

// co the mo mk
@Override
public void createPasswordResetTokenForUser(final TaiKhoan user, final String token) {
	final PasswordResetToken myToken = new PasswordResetToken(token, user);
	passwordResetTokenRepository.save(myToken);
}


@Override
public TaiKhoan save(TaiKhoan entity) {
	return taikhoanRepository.save(entity);
}

@Override
public List<TaiKhoan> saveAll(List<TaiKhoan> entities) {
	return (List<TaiKhoan>)taikhoanRepository.saveAll(entities);
}

@Override
public Optional<TaiKhoan> findById(String id) {
	return taikhoanRepository.findById(id);
}

@Override
public boolean existsById(String id) {
	return taikhoanRepository.existsById(id);
}

@Override
public Iterable<TaiKhoan> findAll() {
	return taikhoanRepository.findAll();
}

@Override
public Iterable<TaiKhoan> findAllById(Iterable<String> ids) {
	return taikhoanRepository.findAllById(ids);
}

@Override
public long count() {
	return taikhoanRepository.count();
}

@Override
public void deleteById(String id) {
	taikhoanRepository.deleteById(id);
}

@Override
public void delete(TaiKhoan entity) {
	taikhoanRepository.delete(entity);
}

public Page<TaiKhoan> findAll(Pageable pageable) {
    return taikhoanRepository.findAll(pageable);
}
@Override
public void deleteAll(List<TaiKhoan> entities) {
	taikhoanRepository.deleteAll(entities);
}

@Override
public void deleteAll() {
	taikhoanRepository.deleteAll();
}

public TaiKhoan findByEmail(String email) {
	return taikhoanRepository.findByEmail(email);
}


public TaiKhoan findByTentaikhoan(String tentaikhoan) {
	return taikhoanRepository.findByTentaikhoan(tentaikhoan);
}

public List<TaiKhoan> findByTentaikhoanLikeOrderByTentaikhoan(String hoten) {
    return taikhoanRepository.findByTentaikhoanLikeOrderByTentaikhoan("%" + hoten+ "%");
}

@Override
@Transactional
public UserDetails loadUserByUsername(String tentaikhoan) throws UsernameNotFoundException {
	 TaiKhoan user = taikhoanRepository.findByTentaikhoan(tentaikhoan);
     if (user == null) {
         throw new UsernameNotFoundException("User not found");
     }

     Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
     grantedAuthorities.add(new SimpleGrantedAuthority("Admin"));

     return new org.springframework.security.core.userdetails.User(
             user.getTentaikhoan(), user.getMatkhau(), grantedAuthorities);
 }

}
