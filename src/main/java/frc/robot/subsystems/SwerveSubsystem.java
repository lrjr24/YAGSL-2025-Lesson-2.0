package frc.robot.subsystems;

import java.io.File;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SwerveConstants;
import swervelib.SwerveDrive;
import swervelib.math.SwerveMath;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;
public class SwerveSubsystem extends SubsystemBase {
    File directory = new File(Filesystem.getDeployDirectory(),"swerve");
    SwerveDrive swerveDrive;
    public SwerveSubsystem(){
        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
        try {
            swerveDrive = new SwerveParser(directory).createSwerveDrive(SwerveConstants.k_maxSpeed);
        }
        catch (Exception o){
            throw new RuntimeException(o);
        }
        swerveDrive.setCosineCompensator(false);
    }
    public Command driveCommand(DoubleSupplier translationX, DoubleSupplier translationY, DoubleSupplier angularRotationX){
        return run(() -> {
        swerveDrive.drive(SwerveMath.scaleTranslation(new Translation2d(
            translationX.getAsDouble() * swerveDrive.getMaximumVelocity(),
            translationY.getAsDouble() * swerveDrive.getMaximumVelocity()), 0.8),
            Math.pow(angularRotationX.getAsDouble(), 3) * swerveDrive.getMaximumAngularVelocity(),
            true,
            false);
        });
    }
    public void periodic() {}
}
