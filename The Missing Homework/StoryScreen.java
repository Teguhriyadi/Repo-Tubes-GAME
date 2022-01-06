import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;

public class StoryScreen extends BaseScreen
{
    Scene scene;
    Background background;
    Kelsoe kelsoe;
    DialogBox dialogBox;
    BaseActor continueKey;
    Table buttonTable;
    BaseActor theEnd;

    public void initialize()
    {
        background = new Background(0,0, mainStage);
        background.setOpacity(0);
        BaseActor.setWorldBounds(background);

        kelsoe = new Kelsoe(0,0, mainStage);

        dialogBox = new DialogBox(0,0, uiStage);
        dialogBox.setDialogSize(600, 150);
        dialogBox.setBackgroundColor( new Color(0.2f, 0.2f, 0.2f, 1) );
        dialogBox.setVisible(false);

        continueKey = new BaseActor(0,0,uiStage);
        continueKey.loadTexture("assets/key-C.png");
        continueKey.setSize(32,32);
        continueKey.setVisible(false);

        dialogBox.addActor(continueKey);
        continueKey.setPosition( dialogBox.getWidth() - continueKey.getWidth(), 0 );

        buttonTable = new Table();
        buttonTable.setVisible(false);

        uiTable.add().expandY();
        uiTable.row();
        uiTable.add(buttonTable);
        uiTable.row();
        uiTable.add(dialogBox);

        // this is being added to the mainStage
        //  so that it does not block access to the buttons
        theEnd = new BaseActor(0,0,mainStage);
        theEnd.loadTexture("assets/the-end.png");
        theEnd.centerAtActor(background);
        theEnd.setScale(2);
        theEnd.setOpacity(0);
        
        scene = new Scene();
        mainStage.addActor(scene);

        hallway();
    }

    public void addTextSequence(String s)
    {
        scene.addSegment( new SceneSegment( dialogBox, SceneActions.typewriter(s) ));
        scene.addSegment( new SceneSegment( continueKey, Actions.show() ));
        scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
        scene.addSegment( new SceneSegment( continueKey, Actions.hide() ));
    }

    public void hallway()
    {
        scene.clearSegments();

        background.setAnimation( background.hallway );
        dialogBox.setText("");
        kelsoe.addAction( SceneActions.moveToOutsideLeft(0) );

        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( kelsoe, SceneActions.moveToScreenCenter(1) )); 
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));

        addTextSequence( "Hello, Nama saya adalah M Ilham Teguhriyadi. Saya kuliah di Politeknik Negeri Indramayu." );
        addTextSequence( "Saya terkadang bisa sedikit lupa. Saat ini, saya sedang mencari pekerjaan rumah saya." );

        scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));
        scene.addSegment( new SceneSegment( kelsoe, SceneActions.moveToOutsideRight(1) )); 
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));

        scene.addSegment( new SceneSegment( background, Actions.run(() -> { classroom(); }) ));

        scene.start();
    }

    public void classroom()
    {
        scene.clearSegments();

        background.setAnimation( background.classroom );
        dialogBox.setText("");
        kelsoe.addAction( SceneActions.moveToOutsideLeft(0) );

        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( kelsoe, SceneActions.moveToScreenCenter(1) )); 
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));

        addTextSequence( "Ini adalah ruang kelas saya. Pekerjaan rumah saya tidak ada di sini" );
        addTextSequence( "Dimana saya harus mencari pekerjaan rumah saya selanjutnya ?" );

        scene.addSegment( new SceneSegment( buttonTable, Actions.show() ));

        // set up options
        TextButton scienceLabButton = new TextButton("Lihat di Lab Sains", BaseGame.textButtonStyle);
        scienceLabButton.addListener(
            (Event e) -> 
            { 
                if ( !(e instanceof InputEvent) || 
                     !((InputEvent)e).getType().equals(Type.touchDown) )
                     return false;

                scene.addSegment( new SceneSegment( buttonTable, Actions.hide() ));
                addTextSequence( "Itu ide yang bagus. Saya akan memeriksa Lab Sains" );
                scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));
                scene.addSegment( new SceneSegment( kelsoe, SceneActions.moveToOutsideLeft(1) )); 
                scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
                scene.addSegment( new SceneSegment( background, Actions.run(() -> { scienceLab(); }) ));

                return false;
            }
        );

        TextButton libraryButton = new TextButton("Lihat di Perpustakaan", BaseGame.textButtonStyle);
        libraryButton.addListener(
            (Event e) -> 
            { 
                if ( !(e instanceof InputEvent) || 
                     !((InputEvent)e).getType().equals(Type.touchDown) )
                     return false;

                scene.addSegment( new SceneSegment( buttonTable, Actions.hide() ));
                addTextSequence( "Itu ide yang bagus. Mungkin saya meninggalkannya di perpustakaan" );
                scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));
                scene.addSegment( new SceneSegment( kelsoe, SceneActions.moveToOutsideLeft(1) )); 
                scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
                scene.addSegment( new SceneSegment( background, Actions.run(() -> { library(); }) ));

                return false;
            }
        );

        buttonTable.clearChildren();
        buttonTable.add(scienceLabButton);
        buttonTable.row();
        buttonTable.add(libraryButton);
        
        scene.start();
    }

    public void scienceLab()
    {
        scene.clearSegments();

        background.setAnimation( background.scienceLab );
        dialogBox.setText("");
        kelsoe.addAction( SceneActions.moveToOutsideLeft(0) );

        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( kelsoe, SceneActions.moveToScreenCenter(1) )); 
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));

        addTextSequence( "Ini adalah laboratorium sains.");
        scene.addSegment( new SceneSegment( kelsoe, SceneActions.setAnimation( kelsoe.sad ) ));
        addTextSequence( "Pekerjaan rumah saya tidak ada disini." );
        scene.addSegment( new SceneSegment( kelsoe, SceneActions.setAnimation( kelsoe.normal ) ));
        addTextSequence( "Sekarang kemana saya harus pergi ?" );

        scene.addSegment( new SceneSegment( buttonTable, Actions.show() ));

        // set up options
        TextButton classroomButton = new TextButton("Kembali ke Kelas", BaseGame.textButtonStyle);
        classroomButton.addListener(
            (Event e) -> 
            { 
                if ( !(e instanceof InputEvent) || 
                     !((InputEvent)e).getType().equals(Type.touchDown) )
                     return false;

                scene.addSegment( new SceneSegment( buttonTable, Actions.hide() ));
                addTextSequence( "Mungkin seseorang menemukannya dan meletakkannya di kelas. Saya akan pergi memeriksa." );
                scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));
                scene.addSegment( new SceneSegment( kelsoe, SceneActions.moveToOutsideRight(1) )); 
                scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
                scene.addSegment( new SceneSegment( background, Actions.run(() -> { classroom(); }) ));

                return false;
            }
        );

        TextButton libraryButton = new TextButton("Lihat di Perpustakaan", BaseGame.textButtonStyle);
        libraryButton.addListener(
            (Event e) -> 
            { 
                if ( !(e instanceof InputEvent) || 
                     !((InputEvent)e).getType().equals(Type.touchDown) )
                     return false;

                scene.addSegment( new SceneSegment( buttonTable, Actions.hide() ));
                addTextSequence( "Itu ide yang bagus. Mungkin saya meninggalkannya di perpustakaan." );
                scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));
                scene.addSegment( new SceneSegment( kelsoe, SceneActions.moveToOutsideRight(1) )); 
                scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
                scene.addSegment( new SceneSegment( background, Actions.run(() -> { library(); }) ));

                return false;
            }
        );

        buttonTable.clearChildren();
        buttonTable.add(classroomButton);
        buttonTable.row();
        buttonTable.add(libraryButton);

        scene.start();
    }

    public void library()
    {
        scene.clearSegments();

        background.setAnimation( background.library );
        dialogBox.setText("");
        kelsoe.addAction( SceneActions.moveToOutsideLeft(0) );

        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( kelsoe, SceneActions.moveToScreenCenter(1) )); 
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));

        addTextSequence( "Ini adalah perpustakaan");
        addTextSequence( "Coba saya cek meja tempat saya bekerja tadi..." );
        scene.addSegment( new SceneSegment( kelsoe, SceneActions.setAnimation( kelsoe.lookRight ) ));
        scene.addSegment( new SceneSegment( kelsoe, SceneActions.moveToScreenRight(2) ));
        scene.addSegment( new SceneSegment( kelsoe, SceneActions.setAnimation( kelsoe.normal ) ));
        addTextSequence( "Ah! Ini dia!" );
        scene.addSegment( new SceneSegment( kelsoe, SceneActions.moveToScreenCenter(0.5f) ));
        addTextSequence( "Terima kasih telah membantu saya menemukannya!" );
        scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));

        scene.addSegment( new SceneSegment( theEnd, Actions.fadeIn(4) ));
        
        scene.addSegment( new SceneSegment( background, Actions.delay(10) ));
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { BaseGame.setActiveScreen(new MenuScreen()); }) ));
        scene.start();
    }

    public void update(float dt)
    {

    }

    public boolean keyDown(int keyCode)
    {
        if ( keyCode == Keys.C ) // && continueKey.isVisible() )
            scene.loadNextSegment();

        return false;
    }
}