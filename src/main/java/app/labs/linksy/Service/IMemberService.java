package app.labs.linksy.Service;

import app.labs.linksy.Model.Member;

public interface IMemberService {
	Member getMemberByUserId(String userId);
}
