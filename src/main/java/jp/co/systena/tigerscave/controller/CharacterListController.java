package jp.co.systena.tigerscave.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.systena.tigerscave.model.Character;
import jp.co.systena.tigerscave.model.CharacterListForm;

@Controller // Viewあり。Viewを返却するアノテーション
public class CharacterListController {

	@Autowired
	JdbcTemplate jdbcTemplate;


	/**
	 * 初期表示用
	 *
	 * キャラクターデータを取得して一覧表示する
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/characterlist", method = RequestMethod.GET) // URLとのマッピング
	public String index(Model model) {

		model.addAttribute("characters", getCharacterList());

		return "characterlist";
	}



	/**
	 * 「更新」ボタン押下時の処理
	 *
	 * 入力された名前と攻撃力をキャラクターIDをキーとして更新する
	 *
	 * @param characterForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/characterlist", method = RequestMethod.POST) // URLとのマッピング
	public String update(@Valid CharacterListForm characterForm,
			BindingResult result,
			Model model) {

		// listFormに画面で入力したデータが入っているので取得する
		List<Character> characterList = characterForm.getCharacterList();
		// ビューに受け渡し用にmodelにセット
		model.addAttribute("characters", characterList);
		model.addAttribute("characterForm", characterForm);


		//画面入力値にエラーがない場合
		if (!result.hasErrors()) {
			if (characterList != null) {
				//画面入力値1行ずつ処理をする
				for (Character character : characterList) {

					//1行分の値でデータベースをUPDATEする
					//character_idをキーに名称と価格を更新する
					//SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
					@SuppressWarnings("unused")
					int updateCount = jdbcTemplate.update(
							"UPDATE characters SET character_name = ?, attackPoint = ? WHERE character_id = ?",
							character.getCharacterName(),
							Integer.parseInt(character.getAttackPoint()),
							Integer.parseInt(character.getCharacterId()));
				}
			}
		}

		return "characterlist";

	}

	/**
	 * 「削除」リンク押下時の処理
	 *
	 * パラメータで受け取ったキャラクターIDのデータを削除する
	 *
	 * @param characterId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deletecharacter", method = RequestMethod.GET) // URLとのマッピング
	public String update(@RequestParam(name = "character_id", required = true) String characterId,
			Model model) {


		// 本来はここで入力チェックなど


		// パラメータで受けとったキャラクターIDのデータを削除する
		// SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
		@SuppressWarnings("unused")
		int deleteCount = jdbcTemplate.update("DELETE FROM characters WHERE character_id = ?", Integer.parseInt(characterId));


		return "redirect:/characterlist";

	}



	/**
	 * 「登録」ボタン押下時の処理
	 *
	 * 入力されたキャラクターID、名前、攻撃力をデータベースに登録する
	 *
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addcharacter", method = RequestMethod.POST) // URLとのマッピング
	public String insert(@Valid jp.co.systena.tigerscave.model.Character form,
			BindingResult result,
			Model model) {

		//画面入力値にエラーがない場合
		if (!result.hasErrors()) {

			//1行分の値をデータベースにINSERTする
			//SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
			@SuppressWarnings("unused")
			int insertCount = jdbcTemplate.update(
					"INSERT INTO characters VALUES( ?, ?, ? )",
					Integer.parseInt(form.getCharacterId()),
					form.getCharacterName(),
					Integer.parseInt(form.getAttackPoint())
					);

		}

		return "redirect:/characterlist";

	}


	/**
	 * データベースからアイテムデータ一覧を取得する
	 *
	 * @return
	 */
	private List<Character> getCharacterList() {

		//SELECTを使用してテーブルの情報をすべて取得する
		List<Character> list = jdbcTemplate.query("SELECT * FROM characters ORDER BY character_id", new BeanPropertyRowMapper<Character>(Character.class));

		return list;

		/*
	    //結果はMapのリストとして取得することもできる
	    List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM characters ORDER BY character_id");

		 */


	}
}

